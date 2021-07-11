/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.api.command.command;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import io.naradrama.prologue.domain.cqrs.command.CqrsBaseCommand;
import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import java.util.List;
import io.naradrama.prologue.domain.NameValueList;
import io.naradrama.prologue.domain.cqrs.command.CqrsBaseCommandType;
import io.naradrama.prologue.util.json.JsonUtil;

@Getter
@Setter
@NoArgsConstructor
public class PostingCommand extends CqrsBaseCommand {
    //
    private PostingCdo postingCdo;
    private List<PostingCdo> postingCdos;
    private boolean multiCdo;
    private String postingId;
    private NameValueList nameValues;

    protected PostingCommand(CqrsBaseCommandType type) {
        //
        super(type);
    }

    public static PostingCommand newRegisterPostingCommand(PostingCdo postingCdo) {
        //
        PostingCommand command = new PostingCommand(CqrsBaseCommandType.Register);
        command.setPostingCdo(postingCdo);
        return command;
    }

    public static PostingCommand newRegisterPostingCommand(List<PostingCdo> postingCdos) {
        //
        PostingCommand command = new PostingCommand(CqrsBaseCommandType.Register);
        command.setPostingCdos(postingCdos);
        command.setMultiCdo(true);
        return command;
    }

    public static PostingCommand newModifyPostingCommand(String postingId, NameValueList nameValues) {
        //
        PostingCommand command = new PostingCommand(CqrsBaseCommandType.Modify);
        command.setPostingId(postingId);
        command.setNameValues(nameValues);
        return command;
    }

    public static PostingCommand newRemovePostingCommand(String postingId) {
        //
        PostingCommand command = new PostingCommand(CqrsBaseCommandType.Remove);
        command.setPostingId(postingId);
        return command;
    }

    public String toString() {
        //
        return toJson();
    }

    public static PostingCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, PostingCommand.class);
    }

    public static PostingCommand sampleForRegister() {
        //
        return newRegisterPostingCommand(PostingCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sampleForRegister());
    }
}
