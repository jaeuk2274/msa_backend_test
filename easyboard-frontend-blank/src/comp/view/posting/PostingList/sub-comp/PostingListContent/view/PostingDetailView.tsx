import React from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box } from '@nara.platform/react-ui';

import { Posting } from '~/comp/api';
import { PostingAction } from '~/comp/view/shared';
import PostingDetail from '../../../../PostingDetail';


interface Props {
  //
  userId: string;
  sourceEntityId: string;
  sourceEntityName: string;
  posting: Posting;
  anonymous: boolean;
  onClickReplies: (posting: Posting) => void;
  onModify: (success: boolean) => void;
  writerName: string;
}

@autobind
@observer
class PostingDetailView extends ReactComponent<Props> {
  //
  render() {
    //
    const { userId, posting, anonymous } = this.props;

    return (
      <Box mb={2}>
        <PostingDetail posting={posting}>
          <PostingDetail.Header
            anonymous={anonymous}
            renderAction={(targetPosting: Posting) => posting.writerId === userId && !posting.deleted && (
              <PostingAction
                postingId={targetPosting.id}
              />
            )}
          />
          <PostingDetail.Content />
        </PostingDetail>
      </Box>
    );
  }
}

export default PostingDetailView;
