import { fromDomain, CqrsBaseQuery } from '@nara.drama/prologue';
import { Posting } from '../../api-model';


@fromDomain
class PostingQuery extends CqrsBaseQuery<Posting> {
  postingId: string;

  constructor(postingId: string) {
    super(Posting);

    this.postingId = postingId;
  }

  static fromDomain(domain: PostingQuery): PostingQuery {
    const query = new PostingQuery(domain.postingId);

    query.setResponse(domain);
    return query;
  }

  static byId(id: string) {
    return new PostingQuery(id);
  }

}

export default PostingQuery;
