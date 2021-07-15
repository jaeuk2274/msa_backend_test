import React from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box } from '@nara.platform/react-ui';

import { Posting } from '~/comp/api';
import { PostingAction } from '~/comp/view/shared';
import PostingDetail from '../../../../PostingDetail';
import { PostingForm } from "~/comp/view";


interface Props {
  //
  userId: string;
  sourceEntityId: string;
  sourceEntityName: string;
  posting: Posting;
  anonymous: boolean;
  onClickReplies: (posting: Posting) => void;
  onClickEdit: (id: string) => void;
  onClickRemove: (id: string) => void;
  onModify: (success: boolean) => void;
  writerName: string;
}

@autobind
@observer
class PostingDetailView extends ReactComponent<Props> {
  //
  render() {
    //
    const { userId, posting, anonymous, onClickEdit, onClickRemove, onModify, onClickReplies } = this.props;

    return (
      <Box mb={2}>
        <PostingDetail posting={posting}>
          <PostingDetail.Header
            anonymous={anonymous}
            renderAction={(targetPosting: Posting) => posting.writerId === userId && !posting.deleted && (
              <PostingAction
                postingId={targetPosting.id}
                onClickEdit={onClickEdit}
                onClickRemove={onClickRemove}
              />
            )}
          />

          {/* NOTE: list content editing */}
          { posting.editing
            ? (<PostingForm
              postingId={posting.id}
              boardId={posting.boardId}
              userId={userId}
              writerName={posting.writerName}
              onClickList={() => onModify(false)}
              onSuccess={() => onModify(true)}
              onFail={() => onModify(false)}
            />)
            : (<PostingDetail.Content/>)
          }



        </PostingDetail>
      </Box>
    );
  }
}

export default PostingDetailView;
