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
import io.naradrama.easyboard.aggregate.posting.store.maria.PostingMariaStore;
import io.naradrama.prologue.domain.NameValueList;
import io.naradrama.prologue.domain.Offset;
import io.naradrama.prologue.domain.cqrs.FailureMessage;
import io.naradrama.prologue.domain.cqrs.command.CommandResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.stream.Collectors;

// TODO: Just follow logic structure and doing all steps

@Service
@Transactional
@RequiredArgsConstructor // final field DI
public class PostingLogic {
    private final PostingMariaStore postingMariaStore;

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
        Posting posting = new Posting(postingCdo);
        postingMariaStore.create(posting);

        return posting.getId();
    }

    public List<String> registerPostings(List<PostingCdo> postingCdos) {
        // TODO: register postings via CDO
        //  1. Register entities from cdo list
        //  2. Return registered entity's ID list.
        List<String> IdList = postingCdos.stream()
                .map(postingCdo -> registerPosting(postingCdo))
                .collect(Collectors.toList());
        return IdList;
    }

    public Posting findPosting(String postingId) {
        // TODO:
        //   1. Find post entity by optimal query condition
        //   2. If result was empty, throw exception in the conditional case
        // NOTE: CrudRepository에서 이미 Optional로 받으니 postingMariaStore 에서 널체크
        return postingMariaStore.retrieve(postingId);
    }

    public List<Posting> findAllPosting(Offset offset) {
        // TODO:
        //  1. FindAll posting elements (entities) by optimal query conditions
        return postingMariaStore.retrieveAll(offset);
    }

    public void modifyPosting(String postingId, NameValueList nameValues) {
        // TODO
        //  1. Modify entity: ID and nameValues
        Posting posting = findPosting(postingId);
        posting.modifyValues(nameValues);
        modifyPosting(posting);
    }

    public void modifyPosting(Posting posting) {
        // TODO
        //  1. Modify entity: By postingID
        postingMariaStore.update(posting);
    }

    public void removePosting(String postingId) {
        // TODO:
        //   1. Remove entity: by postingID
        postingMariaStore.delete(postingId);
     }

    public boolean exists(String postingId) {
         // TODO
        //   1 . Checking Entity: By postingID
        return postingMariaStore.exists(postingId);
    }

    // INFO: The throw comment and just use it.
    public List<Posting> findAllByBoardId(String boardId) {
        //
        return postingMariaStore.retrieveAllByBoardId(boardId);
    }

    public List<Posting> deleteByBoardId(String boardId) {
        //
        return postingMariaStore.deleteByBoardId(boardId);
    }



}
