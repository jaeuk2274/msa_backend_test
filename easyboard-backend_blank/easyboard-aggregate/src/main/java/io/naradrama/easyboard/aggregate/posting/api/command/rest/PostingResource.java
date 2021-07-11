/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.command.rest;

import io.naradrama.easyboard.aggregate.posting.api.command.command.PostingCommand;
import io.naradrama.easyboard.aggregate.posting.domain.logic.PostingLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregate/posting")
@RequiredArgsConstructor // final field DI
public class PostingResource implements PostingFacade {
    //
    private final PostingLogic postingLogic;

    @PostMapping("/posting/command")
    public PostingCommand executePosting(@RequestBody PostingCommand postingCommand) {
        //
        return postingLogic.routeCommand(postingCommand);
    }

    // Info: Just follow structure and fixed in PostingResource(a., b.)
    //  a. Implement RestController(add anotations) components;
    //  b. which implement PostingFacade and receive PostingLogic components as Dependency Injection;

    // TODO: Based on Info: Just follow URL structure
    //   URL
    //   1. Root: /aggregate/posting
    //   2. command with Posting: /posting/command

}
