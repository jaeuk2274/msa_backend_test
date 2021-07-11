import { CqrsBaseCommandType, CqrsBaseCommand, NameValueList } from '@nara.drama/prologue';
import { PostingCdo } from '../../api-model';


class PostingCommand extends CqrsBaseCommand {
  postingCdo: PostingCdo | null = null;
  postingCdos: PostingCdo[] = [];
  postingId: string | null = null;
  nameValues: NameValueList | null = null;

  static newRegisterPostingCommand(postingCdo: PostingCdo): PostingCommand {
    const command = new PostingCommand(CqrsBaseCommandType.Register);

    command.postingCdo = postingCdo;
    return command;
  }

  static newRegisterPostingCommands(postingCdos: PostingCdo[]): PostingCommand {
    const command = new PostingCommand(CqrsBaseCommandType.Register);

    command.postingCdos = postingCdos;
    return command;
  }

  static newModifyPostingCommand(postingId: string, nameValues: NameValueList): PostingCommand {
    const command = new PostingCommand(CqrsBaseCommandType.Modify);

    command.postingId = postingId;
    command.nameValues = nameValues;
    return command;
  }

  static newRemovePostingCommand(postingId: string): PostingCommand {
    const command = new PostingCommand(CqrsBaseCommandType.Remove);

    command.postingId = postingId;
    return command;
  }

}

export default PostingCommand;
