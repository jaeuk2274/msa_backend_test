/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.BoardJpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardMariaRepository extends PagingAndSortingRepository<BoardJpo, String> {
    /* Autogen by nara studio */
    Page<BoardJpo> findAll(Pageable pageable);
}
