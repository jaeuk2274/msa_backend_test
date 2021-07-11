import { CqrsUserCommand } from '@nara.drama/prologue';
import { PostingCdo } from '../../../posting';


class RegisterPostingCommand extends CqrsUserCommand {
  postingCdo: PostingCdo;

  constructor(postingCdo: PostingCdo) {
    super();
    this.postingCdo = postingCdo;
  }

  static new(postingCdo: PostingCdo) {
    const command = new RegisterPostingCommand(
      postingCdo,
    );
    return command;
  }

}

export default RegisterPostingCommand;
