import { runInAction } from 'mobx';
import {
  CommandResponse, makeExtendedObservable,
  mobxService,
  NameValueList,
  NotInstantiatedException,
  Operator,
  QueryParam,
} from '@nara.drama/prologue';
import {
  Posting,
  PostingApiStub,
  PostingCdo,
  PostingDynamicQuery,
  PostingFlowApiStub,
  PostingQuery,
  PostingQueryApiStub,
} from '../../../api';


@mobxService
class PostingStateKeeper {
  //
  static readonly instanceName = 'postingStateKeeper';
  static readonly serviceName = 'easyboard.posting.postingStateKeeper';
  static instance: PostingStateKeeper;

  private readonly postingApi: PostingApiStub;
  private readonly postingFlowApi: PostingFlowApiStub;
  private readonly postingQueryApi: PostingQueryApiStub;

  posting: Posting | null = null;

  constructor(
    postingApi: PostingApiStub = PostingApiStub.instance,
    postingFlowApi: PostingFlowApiStub = PostingFlowApiStub.instance,
    postingQueryApi: PostingQueryApiStub = PostingQueryApiStub.instance
  ) {
    this.postingApi = postingApi;
    this.postingFlowApi = postingFlowApi;
    this.postingQueryApi = postingQueryApi;

    makeExtendedObservable(this);
  }

  async register(postingCdo: PostingCdo): Promise<CommandResponse> {
    return this.postingFlowApi.registerPosting(postingCdo);
  }

  async modify(postingId: string, nameValues: NameValueList): Promise<CommandResponse> {
    return this.postingApi.modifyPosting(postingId, nameValues);
  }

  async remove(postingId: string): Promise<CommandResponse> {
    return this.postingFlowApi.removePosting(postingId);
  }

  async findPostingById(postingId: string): Promise<Posting> {
    const postingQuery = PostingQuery.byId(postingId);
    const posting = await this.postingQueryApi.executePostingQuery(postingQuery);

    runInAction(() => this.posting = posting);
    return posting;
  }

  async findPostingByBoardIdAndWriterId(boardId: string, writerId: string): Promise<Posting | null> {
    //
    const query = PostingDynamicQuery.multiParams<Posting>(
      QueryParam.andParam('boardId', Operator.Equal, boardId),
      QueryParam.endParam('writerId', Operator.Equal, writerId)
    );

    const posting = await this.postingQueryApi.executePostingDynamicQuery(query)
      .catch(() => null);

    runInAction(() => this.posting = posting);

    return posting;
  }

  setPostingProp(name: keyof Posting, value: any) {
    if (!this.posting) {
      throw new NotInstantiatedException('PostingAgent.setPostingProp', 'posting is null');
    }

    (this.posting as any)[name] = value;
  }

  initPosting(writerId: string, writerName: string, boardId: string) {
    //
    this.posting = new Posting(writerId, writerName, '', '', '', boardId, false, false, 0);
  }

  clear() {
    this.posting = null;
  }
}


PostingStateKeeper.instance = new PostingStateKeeper();

export default PostingStateKeeper;
