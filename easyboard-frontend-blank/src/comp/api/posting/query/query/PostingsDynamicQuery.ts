import { fromDomain, CqrsDynamicQuery } from '@nara.drama/prologue';
import { Posting } from '../../api-model';


@fromDomain
class PostingsDynamicQuery extends CqrsDynamicQuery<Posting> {
  static fromDomain(domain: PostingsDynamicQuery): PostingsDynamicQuery {
    const query = new PostingsDynamicQuery();

    query.setResponse(domain);
    return query;
  }

}

export default PostingsDynamicQuery;
