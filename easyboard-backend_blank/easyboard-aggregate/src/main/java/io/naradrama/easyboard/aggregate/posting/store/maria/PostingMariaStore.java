/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria;

import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import io.naradrama.easyboard.aggregate.posting.store.maria.repository.PostingMariaRepository;
import io.naradrama.prologue.domain.Offset;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo.toDomains;

@Repository
@RequiredArgsConstructor // final field DI
public class PostingMariaStore implements PostingStore {
    /* Autogen by nara studio */
    private final PostingMariaRepository postingMariaRepository;

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

    @Override
    public void create(Posting posting) {
        //
        postingMariaRepository.save(new PostingJpo(posting));
    }

    @Override
    public Posting retrieve(String id) {
        //
        return postingMariaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Posting Not Found " + id))
                .toDomain();
    }

    @Override
    public List<Posting> retrieveAll(Offset offset) {
        //
        Pageable pageable = createPageable(offset);
        return toDomains(postingMariaRepository.findAll(pageable).getContent());
    }

    @Override
    public void update(Posting posting) {
        //
        postingMariaRepository.save(new PostingJpo(posting));
    }

    @Override
    public void delete(Posting posting) {
        //
        postingMariaRepository.delete(new PostingJpo(posting));
    }

    @Override
    public void delete(String id) {
        //
        postingMariaRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return postingMariaRepository.existsById(id);
    }

    // INFO: throws the without comment and use just it.
    @Override
    public List<Posting> retrieveAllByBoardId(String boardId) {
        //
        return toDomains(postingMariaRepository.findAllByBoardId(boardId));
    }

    @Override
    public List<Posting> deleteByBoardId(String boardId) {
        //
        return toDomains(postingMariaRepository.deleteByBoardId(boardId));
    }



}
