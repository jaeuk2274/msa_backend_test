import React from 'react';
import { observer } from 'mobx-react';
import { NextRouter, withRouter } from 'next/router';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { PostingForm } from '../../../comp/view';


interface Props {
  router: NextRouter;
}

interface InjectedProps {
}
@observer
@autobind
class PostingRegistrationPage extends ReactComponent<Props, {}, InjectedProps> {
  //
  routeToList() {
    //
    // TODO: 게시글 목록 페이지로 이동

  }

  render() {
    //
    const boardId = 'testBoardId';
    const userId = 'manager@nextree.io';
    const writerName = 'testName';

    return (
      <PostingForm
        boardId={boardId}
        userId={userId}
        writerName={writerName}
        onClickList={this.routeToList}
        onSuccess={this.routeToList}
      />
    );
  }
}

export default withRouter(PostingRegistrationPage);
