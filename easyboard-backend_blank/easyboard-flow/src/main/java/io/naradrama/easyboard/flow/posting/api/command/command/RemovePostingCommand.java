/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.flow.posting.api.command.command;

import io.naradrama.prologue.domain.cqrs.command.CqrsUserCommand;
import io.naradrama.prologue.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemovePostingCommand extends CqrsUserCommand {
    //
    private String postingId;

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RemovePostingCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RemovePostingCommand.class);
    }

    public static RemovePostingCommand sample() {
        //
        String postingId = UUID.randomUUID().toString();
        RemovePostingCommand sample = new RemovePostingCommand();
        sample.postingId = postingId;
        return sample;
    }
}
