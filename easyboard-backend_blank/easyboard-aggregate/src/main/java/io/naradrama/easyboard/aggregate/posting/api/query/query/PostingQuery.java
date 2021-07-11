/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.query.query;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import io.naradrama.prologue.domain.cqrs.query.CqrsBaseQuery;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;

@Getter
@Setter
@NoArgsConstructor
public class PostingQuery extends CqrsBaseQuery<Posting> {
    /* Autogen by nara studio */
    private String postingId;

    public void execute(PostingStore postingStore) {
        /* Autogen by nara studio */
        setQueryResult(postingStore.retrieve(postingId));
    }
}
