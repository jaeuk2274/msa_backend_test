import { CqrsUserCommand } from '@nara.drama/prologue';


class RemovePostingCommand extends CqrsUserCommand {
  postingId: string;

  constructor(postingId: string) {
    super();
    this.postingId = postingId;
  }

  static new(postingId: string) {
    const command = new RemovePostingCommand(
      postingId,
    );
    return command;
  }

}

export default RemovePostingCommand;
