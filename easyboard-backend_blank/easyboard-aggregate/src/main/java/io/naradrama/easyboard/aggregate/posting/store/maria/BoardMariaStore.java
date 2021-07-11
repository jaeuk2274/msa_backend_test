/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria;

import org.springframework.stereotype.Repository;
import io.naradrama.easyboard.aggregate.posting.store.BoardStore;
import io.naradrama.easyboard.aggregate.posting.store.maria.repository.BoardMariaRepository;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Board;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.BoardJpo;
import java.util.Optional;
import java.util.List;
import io.naradrama.prologue.domain.Offset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;

@Repository
public class BoardMariaStore implements BoardStore {
    /* Autogen by nara studio */
    private final BoardMariaRepository boardMariaRepository;

    public BoardMariaStore(BoardMariaRepository boardMariaRepository) {
        /* Autogen by nara studio */
        this.boardMariaRepository = boardMariaRepository;
    }

    @Override
    public void create(Board board) {
        /* Autogen by nara studio */
        BoardJpo boardJpo = new BoardJpo(board);
        boardMariaRepository.save(boardJpo);
    }

    @Override
    public Board retrieve(String id) {
        /* Autogen by nara studio */
        Optional<BoardJpo> boardJpo = boardMariaRepository.findById(id);
        return boardJpo.map(BoardJpo::toDomain).orElse(null);
    }




}
