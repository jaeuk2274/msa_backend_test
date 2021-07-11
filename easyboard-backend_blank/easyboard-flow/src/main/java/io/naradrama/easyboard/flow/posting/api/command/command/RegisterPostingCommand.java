/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.flow.posting.api.command.command;

import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import io.naradrama.prologue.domain.cqrs.command.CqrsUserCommand;
import io.naradrama.prologue.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostingCommand extends CqrsUserCommand {
    //
    private PostingCdo postingCdo;


    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RegisterPostingCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RegisterPostingCommand.class);
    }

    public static RegisterPostingCommand sample() {
        //
        PostingCdo postingCdo = PostingCdo.sample();
        RegisterPostingCommand sample = new RegisterPostingCommand();
        sample.setPostingCdo(postingCdo);
        return sample;
    }
}
