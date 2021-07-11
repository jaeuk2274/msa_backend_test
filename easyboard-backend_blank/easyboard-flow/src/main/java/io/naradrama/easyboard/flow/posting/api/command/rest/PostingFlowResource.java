/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.flow.posting.api.command.rest;

import io.naradrama.easyboard.flow.posting.api.command.command.*;
import io.naradrama.easyboard.flow.posting.domain.logic.PostingFlowLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow/posting-flow")
@RequiredArgsConstructor
public class PostingFlowResource implements PostingFlowFacade {
    //
    private final PostingFlowLogic postingFlowLogic;

    @Override
    @PostMapping("/register-posting")
    public RegisterPostingCommand registerPosting(@RequestBody RegisterPostingCommand command) {
        //
        return postingFlowLogic.registerPosting(command);
    }

    @Override
    @PostMapping("/remove-posting")
    public RemovePostingCommand removePosting(@RequestBody RemovePostingCommand command) {
        //
        return postingFlowLogic.removePosting(command);
    }

}
