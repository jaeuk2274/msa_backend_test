/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store;

import io.naradrama.easyboard.aggregate.posting.domain.entity.Board;
import java.util.List;
import io.naradrama.prologue.domain.Offset;

public interface BoardStore {
    /* Autogen by nara studio */
    void create(Board board);
    Board retrieve(String id);
}
