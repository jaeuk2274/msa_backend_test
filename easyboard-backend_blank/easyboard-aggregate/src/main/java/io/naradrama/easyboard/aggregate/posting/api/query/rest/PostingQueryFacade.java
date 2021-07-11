/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.query.rest;

import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingDynamicQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingsDynamicQuery;

public interface PostingQueryFacade {
    /* Autogen by nara studio */
    PostingQuery execute(PostingQuery postingQuery);
    PostingDynamicQuery execute(PostingDynamicQuery postingDynamicQuery);
    PostingsDynamicQuery execute(PostingsDynamicQuery postingsDynamicQuery);
}
