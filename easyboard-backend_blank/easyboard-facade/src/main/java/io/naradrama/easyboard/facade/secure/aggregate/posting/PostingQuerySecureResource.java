/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.facade.secure.aggregate.posting;

import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingDynamicQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.query.PostingsDynamicQuery;
import io.naradrama.easyboard.aggregate.posting.api.query.rest.PostingQueryFacade;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
import io.naradrama.easyboard.aggregate.posting.store.maria.jpo.PostingJpo;
import io.naradrama.prologue.util.query.RdbQueryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

public class PostingQuerySecureResource  {

    // Info: Just follow structure and fixed in PostingQuerySecureResource(a., b.)
    //  a. The extract APIs from PostingQueryResource which are used by Frontend project
    //  b. Replace 'aggregate' to 'secure' of the original URL (e.g. /aggregate/posting/~ => /secure/posting/~)

    // TODO: Based on Info: Just follow URL structure
    //   URL
    //  1. /aggregate/posting/~ => /secure/posting/~

}
