/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.query.rest;

import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingDynamicQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingsDynamicQuery;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import io.naradrama.prologue.util.query.RdbQueryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RequestMapping("/aggregate/posting/query")
@RestController
public class PostingQueryResource implements PostingQueryFacade {
    //
    private PostingStore postingStore;
    private RdbQueryRequest<PostingJpo> rdbQueryRequest;

    public PostingQueryResource(PostingStore postingStore, EntityManager entityManager){
        this.postingStore = postingStore;
        this.rdbQueryRequest = new RdbQueryRequest<>(entityManager);
    }

    @PostMapping("/")
    public PostingQuery execute(@RequestBody PostingQuery postingQuery) {
        //
        postingQuery.execute(postingStore);
        return postingQuery;
    }

    @PostMapping("/dynamic-single")
    public PostingDynamicQuery execute(@RequestBody PostingDynamicQuery postingDynamicQuery) {
        //
        postingDynamicQuery.execute(rdbQueryRequest);
        return postingDynamicQuery;
    }

    @PostMapping("/dynamic-multi")
    public PostingsDynamicQuery execute(@RequestBody PostingsDynamicQuery postingsDynamicQuery) {
        //
        postingsDynamicQuery.execute(rdbQueryRequest);
        return postingsDynamicQuery;
    }


    // Info: Just follow structure and fixed in PostingQueryResource(a., b., c.)
    //  a. Implement RestController(add anotations) components;
    //  b. which implement PostingQueryFacade and receive PostingStore components;
    //  c. Use: EntityManager as Dependency Injection

    // TODO: Based on Info: Just follow URL structure
    //   URL
    //   1. Root: /aggregate/posting/query
    //   2. Base query : /
    //   3. Dynamic query with single result: /dynamic-single
    //   4. Dynamic query with multiple results:/dynamic-multi

}
