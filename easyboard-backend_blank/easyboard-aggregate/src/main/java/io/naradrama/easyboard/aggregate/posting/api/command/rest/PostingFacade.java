/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.command.rest;

import io.naradrama.easyboard.aggregate.posting.api.command.command.PostingCommand;

public interface PostingFacade {
    /* Autogen by nara studio */
    PostingCommand executePosting(PostingCommand postingCommand);

}
