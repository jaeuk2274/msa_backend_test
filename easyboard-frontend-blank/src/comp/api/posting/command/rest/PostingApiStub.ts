import { ApiClient, CommandResponse, NameValueList } from '@nara.drama/prologue';
import {
  PostingCommand,
} from '../command';
import { PostingCdo } from '../../api-model';


class PostingApiStub {
  static instance: PostingApiStub;
  private readonly client = new ApiClient('/api/easyboard/secure/posting', { resDataName: 'commandResponse' });

  async registerPosting(postingCdo: PostingCdo): Promise<CommandResponse> {
    const command = PostingCommand.newRegisterPostingCommand(postingCdo);
    return this.executePosting(command);
  }

  async registerPostings(postingCdos: PostingCdo[]): Promise<CommandResponse> {
    const command = PostingCommand.newRegisterPostingCommands(postingCdos);
    return this.executePosting(command);
  }

  async modifyPosting(postingId: string, nameValues: NameValueList): Promise<CommandResponse> {
    const command = PostingCommand.newModifyPostingCommand(postingId, nameValues);
    return this.executePosting(command);
  }

  async removePosting(postingId: string): Promise<CommandResponse> {
    const command = PostingCommand.newRemovePostingCommand(postingId);
    return this.executePosting(command);
  }

  async executePosting(postingCommand: PostingCommand): Promise<CommandResponse> {
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/posting/command', postingCommand);
  }

}

PostingApiStub.instance = new PostingApiStub();

export default PostingApiStub;
