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
package io.naradrama.easyboard.aggregate.posting.domain.logic;

import io.naradrama.easyboard.aggregate.posting.api.command.command.PostingCommand;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
import io.naradrama.prologue.domain.NameValueList;
import io.naradrama.prologue.domain.Offset;
import io.naradrama.prologue.domain.cqrs.FailureMessage;
import io.naradrama.prologue.domain.cqrs.command.CommandResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

// TODO: Just follow logic structure and doing all steps

@Service
@Transactional
public class PostingLogic {


    public PostingCommand routeCommand(PostingCommand command) {
        switch(/* Autogen by nara studio */
        command.getCqrsBaseCommandType()) {
            case Register:
                if (command.getPostingCdos().size() > 0) {
                    List<String> entityIds = this.registerPostings(command.getPostingCdos());
                    command.setCommandResponse(new CommandResponse(entityIds));
                } else {
                    String entityId = this.registerPosting(command.getPostingCdo());
                    command.setCommandResponse(new CommandResponse(entityId));
                }
                break;
            case Modify:
                this.modifyPosting(command.getPostingId(), command.getNameValues());
                command.setCommandResponse(new CommandResponse(command.getPostingId()));
                break;
            case Remove:
                this.removePosting(command.getPostingId());
                command.setCommandResponse(new CommandResponse(command.getPostingId()));
                break;
            default:
                command.setFailureMessage(new FailureMessage(new Throwable("CommandType must be Register, Modify or Remove")));
        }
        return command;
    }

    public String registerPosting(PostingCdo postingCdo) {
        // TODO:
        //  1. Save entity from cdo when entity was not exists
        //  2. Return registered entity's id

        return null;
    }

    public List<String> registerPostings(List<PostingCdo> postingCdos) {

        // TODO: register postings via CDO
        //  1. Register entities from cdo list
        //  2. Return registered entity's ID list.

        return null;
    }

    public Posting findPosting(String postingId) {

        // TODO:
        //   1. Find post entity by optimal query condition
        //   2. If result was empty, throw exception in the conditional case

        return null;
    }

    public List<Posting> findAllPosting(Offset offset) {

        // TODO:
        //  1. FindAll posting elements (entities) by optimal query conditions

        return null;
    }

    public void modifyPosting(String postingId, NameValueList nameValues) {

        // TODO
        //  1. Modify entity: ID and nameValues

    }

    public void modifyPosting(Posting posting) {

        // TODO
        //  1. Modify entity: By postingID
    }

    public void removePosting(String postingId) {
         // TODO:
        //   1. Remove entity: by postingID
     }

    public boolean exists(String postingId) {
         // TODO
        //   1 . Checking Entity: By postingID

        return false;

    }

    // INFO: The throw comment and just use it.
 /*
    public List<Posting> findAllByBoardId(String boardId) {
        //
        return postingStore.retrieveAllByBoardId(boardId);
    }

    public List<Posting> deleteByBoardId(String boardId) {
        //
        return postingStore.deleteByBoardId(boardId);
    }


  */
}
