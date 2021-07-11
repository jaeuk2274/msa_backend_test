import { ApiClient, OffsetElementList } from '@nara.drama/prologue';
import { Posting } from '../../api-model';
import { PostingQuery, PostingDynamicQuery, PostingsDynamicQuery } from '../../query';



class PostingQueryApiStub {
  static instance: PostingQueryApiStub;
  private readonly client = new ApiClient('/api/easyboard/secure/posting/query', { resDataName: 'queryResult' });

  async executePostingQuery(query: PostingQuery): Promise<Posting> {
    return this.client.postNotNull<Posting>(
      Posting,
      '/',
      query
    );
  }

  async executePostingDynamicQuery(query: PostingDynamicQuery): Promise<Posting | null> {
    return this.client.postNullable<Posting>(
      Posting,
      '/dynamic-single',
      query
    );
  }

  async executePostingsDynamicQuery(query: PostingsDynamicQuery): Promise<Posting[]> {
    return this.client.postArray<Posting>(
      Posting,
      '/dynamic-multi',
      query
    );
  }

  async executePostingsDynamicPagingQuery(query: PostingsDynamicQuery): Promise<OffsetElementList<Posting>> {
    //
    return this.client.postOffsetElementList<Posting>(
      OffsetElementList,
      '/dynamic-multi',
      query,
    );
  }
}

PostingQueryApiStub.instance = new PostingQueryApiStub();

export default PostingQueryApiStub;
