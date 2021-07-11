/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.flow.posting.domain.logic;

import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import io.naradrama.easyboard.aggregate.posting.domain.logic.PostingLogic;
import io.naradrama.easyboard.flow.posting.api.command.command.RegisterPostingCommand;
import io.naradrama.easyboard.flow.posting.api.command.command.RemovePostingCommand;
import io.naradrama.prologue.domain.cqrs.command.CommandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingFlowLogic {
    //
    private final PostingLogic postingLogic;

    public RegisterPostingCommand registerPosting(RegisterPostingCommand command) {
        //





        PostingCdo postingCdo = command.getPostingCdo();


        System.out.println("1 : " + postingCdo.getBoardId());
        PostingCdo postingCdo1 = command.getPostingCdo();
        System.out.println("2 : " + postingCdo1.getBoardId());
        PostingCdo postingCdo2 = command.getPostingCdo();
        System.out.println("3 : " + postingCdo2.getBoardId());

        List<PostingCdo> p = new ArrayList<PostingCdo>();
        p.add(postingCdo);
        p.add(postingCdo1);
        p.add(postingCdo2);

        postingLogic.registerPostings(p);



        String postingId = postingLogic.registerPosting(postingCdo);

        command.setCommandResponse(new CommandResponse(postingId));
        return command;
    }

    public RemovePostingCommand removePosting(RemovePostingCommand command) {
        //
        String postingId = command.getPostingId();
        Posting posting = postingLogic.findPosting(postingId);

        posting.markDeleted();
        postingLogic.modifyPosting(posting);

        command.setCommandResponse(new CommandResponse(postingId));
        return command;
    }
}
