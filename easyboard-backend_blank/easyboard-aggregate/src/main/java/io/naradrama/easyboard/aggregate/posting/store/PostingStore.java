/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store;

import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import java.util.List;
import io.naradrama.prologue.domain.Offset;

public interface PostingStore {
    /* Autogen by nara studio */
    void create(Posting posting);
    Posting retrieve(String id);
    List<Posting> retrieveAll(Offset offset);
    void update(Posting posting);
    void delete(Posting posting);
    void delete(String id);
    boolean exists(String id);

    List<Posting> retrieveAllByBoardId(String boardId);
    List<Posting> deleteByBoardId(String boardId);
}
