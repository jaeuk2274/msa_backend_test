/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria.jpo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import io.naradrama.prologue.store.jpa.DomainEntityJpo;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Board;
import org.springframework.beans.BeanUtils;
import io.naradrama.prologue.util.json.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOARD")
public class BoardJpo extends DomainEntityJpo {
    //
    private String writerId;
    private String writerName;
    private String title;
    private boolean important;
    private boolean deleted;
    private long time;
    private String postId;

    public BoardJpo(Board board) {
        //
        super(board);
        BeanUtils.copyProperties(board, this);
    }

    public Board toDomain() {
        //
        Board board = new Board(getId());
        BeanUtils.copyProperties(this, board);
        return board;
    }

    public static List<Board> toDomains(List<BoardJpo> boardJpos) {
        //
        return boardJpos.stream().map(BoardJpo::toDomain).collect(Collectors.toList());
    }

    public String toString() {
        //
        return toJson();
    }

    public static BoardJpo sample() {
        //
        return new BoardJpo(Board.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}
