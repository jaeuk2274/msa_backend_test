import React from 'react';
import { observer } from 'mobx-react';
import { NextRouter, withRouter } from 'next/router';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box } from '@material-ui/core';
import { PostingForm } from '../../../../comp/view';
import { PostingsStateKeeper, PostingStateKeeper } from '~/comp/state';


interface Props {
  router: NextRouter;
}

interface InjectedProps {
  postingsStateKeeper: PostingsStateKeeper;
  postingStateKeeper: PostingStateKeeper;
}

@observer
@autobind
class PostingEditPage extends ReactComponent<Props, {}, InjectedProps> {
  //
  getFromPath() {
    //
    const { query } = this.props.router;

    return {
      postingId: query.postingId as string,
    };
  }

  routeToList() {
    //
    // TODO: 게시글 목록 페이지로 이동

  }

  render() {
    //
    const { postingId } = this.getFromPath();
    const boardId = 'testBoardId';
    const userId = 'manager@nextree.io';
    const writerName = 'testName';

    return (
      <Box>
        <h3>Edit</h3>
        <PostingForm
          postingId={postingId}
          boardId={boardId}
          userId={userId}
          writerName={writerName}
          onClickList={this.routeToList}
          onSuccess={this.routeToList}
        />
      </Box>
    );
  }
}

export default withRouter(PostingEditPage);
