import { ApiClient, CommandResponse } from '@nara.drama/prologue';
import {
  RegisterPostingCommand,
  RemovePostingCommand,
} from '../command';
import {
  PostingCdo,
} from '../../../posting';


class PostingFlowApiStub {
  static instance: PostingFlowApiStub;
  private readonly client = new ApiClient('/api/easyboard/secure/posting-flow', { resDataName: 'commandResponse' });

  async registerPosting(postingCdo: PostingCdo): Promise<CommandResponse> {
    const command = RegisterPostingCommand.new(
      postingCdo,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/register-posting', command);
  }

  async removePosting(postingId: string): Promise<CommandResponse> {
    const command = RemovePostingCommand.new(
      postingId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/remove-posting', command);
  }
}

PostingFlowApiStub.instance = new PostingFlowApiStub();

export default PostingFlowApiStub;
