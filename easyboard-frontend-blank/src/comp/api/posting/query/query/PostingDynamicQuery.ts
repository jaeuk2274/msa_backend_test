import { fromDomain, CqrsDynamicQuery } from '@nara.drama/prologue';
import { Posting } from '../../api-model';


@fromDomain
class PostingDynamicQuery extends CqrsDynamicQuery<Posting> {
  static fromDomain(domain: PostingDynamicQuery): PostingDynamicQuery {
    const query = new PostingDynamicQuery();

    query.setResponse(domain);
    return query;
  }

}

export default PostingDynamicQuery;
