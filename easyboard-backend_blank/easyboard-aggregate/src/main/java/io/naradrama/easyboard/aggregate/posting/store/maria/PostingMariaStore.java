/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria;

import org.springframework.stereotype.Repository;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
import io.naradrama.easyboard.aggregate.posting.store.maria.repository.PostingMariaRepository;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import java.util.Optional;
import java.util.List;
import io.naradrama.prologue.domain.Offset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;

@Repository
public class PostingMariaStore {
    /* Autogen by nara studio */

    // TODO: Follow structure
    //   1. Implement Repository PostingStore components.
    //   2. PostingMariaRepository receives as Dependency Injection.




    private Pageable createPageable(Offset offset) {
        /* Autogen by nara studio */
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(), (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC), offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }

    // INFO: throws the without comment and use just it.
/*
    @Override
    public List<Posting> retrieveAllByBoardId(String boardId) {

        return PostingJpo.toDomains(postingMariaRepository.findAllByBoardId(boardId));
    }

    @Override
    public List<Posting> deleteByBoardId(String boardId) {

        return PostingJpo.toDomains(postingMariaRepository.deleteByBoardId(boardId));
    }
   */
}
