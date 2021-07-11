/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.query.query;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import io.naradrama.prologue.domain.cqrs.query.CqrsDynamicQuery;
import java.util.List;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.prologue.util.query.RdbQueryRequest;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import io.naradrama.prologue.util.query.RdbQueryBuilder;
import javax.persistence.TypedQuery;
import io.naradrama.prologue.domain.Offset;
import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
public class PostingsDynamicQuery extends CqrsDynamicQuery<List<Posting>> {
    /* Autogen by nara studio */

    public void execute(RdbQueryRequest<PostingJpo> request) {
        /* Autogen by nara studio */
        request.addQueryStringAndClass(genSqlString(), PostingJpo.class);
        Offset offset = getOffset();
        TypedQuery<PostingJpo> query = RdbQueryBuilder.build(request, offset);
        query.setFirstResult(offset.getOffset());
        query.setMaxResults(offset.getLimit());
        List<PostingJpo> postingJpos = query.getResultList();
        setQueryResult(PostingJpo.toDomains(postingJpos));
        if (nonNull(getOffset()) && getOffset().isTotalCountRequested()) {
            TypedQuery<Long> countQuery = RdbQueryBuilder.buildForCount(request);
            long totalCount = countQuery.getSingleResult();
            Offset countableOffset = getOffset();
            countableOffset.setTotalCount((int) totalCount);
            setOffset(countableOffset);
        }
    }
}
