/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.facade.secure.flow.posting;

import io.naradrama.easyboard.flow.posting.api.command.command.*;
import io.naradrama.easyboard.flow.posting.api.command.rest.PostingFlowFacade;
import io.naradrama.easyboard.flow.posting.domain.logic.PostingFlowLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Extract Apis from PostingFlowResource which are used by front project
//  replace 'flow' to 'secure' of origin url (e.g. /flow/posting-flow/~ => /secure/posting-flow/~)
@RestController
@RequestMapping("/secure/posting-flow")
@RequiredArgsConstructor
public class PostingFlowSecureResource implements PostingFlowFacade {
    //
    private final PostingFlowLogic postingFlowLogic;

    @Override
    @PostMapping("/register-posting")
    public RegisterPostingCommand registerPosting(@RequestBody RegisterPostingCommand command) {
        return postingFlowLogic.registerPosting(command);
    }

    @Override
    @PostMapping("/remove-posting")
    public RemovePostingCommand removePosting(@RequestBody RemovePostingCommand command) {
        return postingFlowLogic.removePosting(command);
    }
}
