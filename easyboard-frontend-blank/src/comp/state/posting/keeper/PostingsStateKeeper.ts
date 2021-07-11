import { runInAction } from 'mobx';
import {
  makeExtendedObservable,
  mobxService,
  Offset,
  OffsetElementList,
  Operator,
  QueryParam,
} from '@nara.drama/prologue';
import { Posting, PostingApiStub, PostingQueryApiStub, PostingsDynamicQuery } from '../../../api';


@mobxService
class PostingsStateKeeper {
  //
  static readonly instanceName = 'postingsStateKeeper';
  static readonly serviceName = 'easyboard.posting.postingsStateKeeper';
  static instance: PostingsStateKeeper;

  private readonly postingApi: PostingApiStub;
  private readonly postingQueryApi: PostingQueryApiStub;

  postings: Posting[] = [];

  offset: Offset = {} as Offset;


  constructor(
    postingApi: PostingApiStub = PostingApiStub.instance,
    postingQueryApi: PostingQueryApiStub = PostingQueryApiStub.instance
  ) {
    this.postingApi = postingApi;
    this.postingQueryApi = postingQueryApi;

    makeExtendedObservable(this);
  }

  async findPostingsByBoardId(boardId: string, offset: Offset): Promise<OffsetElementList<Posting>> {
    //
    const query = PostingsDynamicQuery.oneParam<Posting>(
      QueryParam.endParam('boardId', Operator.Equal, boardId)
    );

    query.offset = offset;

    return this.findPostings(query);
  }

  async findNotDeletedPostingsByBoardId(boardId: string, offset: Offset): Promise<OffsetElementList<Posting>> {
    //
    const query = PostingsDynamicQuery.multiParams<Posting>(
      QueryParam.andParam('boardId', Operator.Equal, boardId),
      QueryParam.endParam('deleted', Operator.Equal, 'false')
    );

    query.offset = offset;

    return this.findPostings(query);
  }

  private async findPostings(query: PostingsDynamicQuery): Promise<OffsetElementList<Posting>> {
    //
    const postingsOffsetElementList = await this.postingQueryApi.executePostingsDynamicPagingQuery(query);

    query.offset.totalCount = postingsOffsetElementList.totalCount;

    runInAction(() => {
      this.postings = postingsOffsetElementList.results;
      this.offset = query.offset;
    });

    return postingsOffsetElementList;
  }

  setPostingsProp(postingId: string, name: keyof Posting, value: any) {
    //

    // TODO: postingId로 게시글 단건의 property 수정

  }

  clear() {
    //
    this.postings = [];
  }
}


PostingsStateKeeper.instance = new PostingsStateKeeper();

export default PostingsStateKeeper;
