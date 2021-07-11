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
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.prologue.util.query.RdbQueryRequest;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import io.naradrama.prologue.util.query.RdbQueryBuilder;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PostingDynamicQuery extends CqrsDynamicQuery<Posting> {
    /* Autogen by nara studio */

    public void execute(RdbQueryRequest<PostingJpo> request) {
        /* Autogen by nara studio */
        request.addQueryStringAndClass(genSqlString(), PostingJpo.class);
        TypedQuery<PostingJpo> query = RdbQueryBuilder.build(request);
        PostingJpo postingJpo = query.getSingleResult();
        setQueryResult(Optional.ofNullable(postingJpo).map(jpo -> jpo.toDomain()).orElse(null));
    }
}
