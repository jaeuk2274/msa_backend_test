/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostingMariaRepository extends PagingAndSortingRepository<PostingJpo, String> {

    // TODO : follow the structure
    // 1. Declare some methods which are required by PostingMariaStore
}
