/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.flow.posting.api.command.rest;

import io.naradrama.easyboard.flow.posting.api.command.command.*;

public interface PostingFlowFacade {
    //
    RegisterPostingCommand registerPosting(RegisterPostingCommand command);
    RemovePostingCommand removePosting(RemovePostingCommand command);

}
