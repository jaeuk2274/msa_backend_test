/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.facade.secure.aggregate.posting;

import io.naradrama.easyboard.aggregate.posting.api.command.command.PostingCommand;
import io.naradrama.easyboard.aggregate.posting.api.command.rest.PostingFacade;
import io.naradrama.easyboard.aggregate.posting.domain.logic.PostingLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/posting")
@RequiredArgsConstructor
public class PostingSecureResource implements PostingFacade {
    //
    private final PostingLogic postingLogic;

    @PostMapping("/posting/command")
    public PostingCommand executePosting(@RequestBody PostingCommand postingCommand) {
        //
        return postingLogic.routeCommand(postingCommand);
    }
    //

    // Info: Just follow structure and fixed in PostingSecureResource(a., b.)
    //  a. The extract APIs from PostingResource which are used by Frontend project
    //  b. Replace 'aggregate' to 'secure' of the original URL (e.g. /aggregate/posting/~ => /secure/posting/~)

    // TODO: Based on Info: Just follow URL structure
    //   URL
    //  1. /aggregate/posting/~ => /secure/posting/~
}
