import React from 'react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { NextRouter, withRouter } from 'next/router';
import { Box, Button, Container, Grid, Paper, SubActions } from '@nara.platform/react-ui';
import { LayoutSidebar, PostingList } from '~/comp/view';


interface Props {
  router: NextRouter;
}

@autobind
class PostingListPage extends ReactComponent<Props> {
  //
  onClickEdit(postingId: string) {
    //
    this.routeToEdit(postingId);
  }

  routeToEdit(postingId: string) {
    //
    // TODO: 게시글 수정 페이지로 이동

  }

  routeToRegistration() {
    //
    // TODO: 게시글 등록 페이지로 이동

  }

  render() {
    //
    const sourceEntityId = 'testBoardId';
    const sourceEntityName = 'Namoosori';
    const userId = 'manager@nextree.io';
    const writerName = 'testName';

    return (
      <Box>
        <Grid container spacing={2} className="root">
          <Grid item xs={2}>
            <LayoutSidebar />
          </Grid>
          <Grid item xs={10}>
            <Grid item xs={12}>
              <Paper elevation={0} variant="outlined" square className="title"><b>Board</b></Paper>
            </Grid>
            <Grid item xs={12} className="postArea">
              <Container>
                <br /><br />
                <PostingList
                  sourceEntityId={sourceEntityId}
                  sourceEntityName={sourceEntityName}
                  userId={userId}
                  writerName={writerName}
                >
                  <SubActions>
                    <SubActions.Left>
                      {/* TODO: 게시글 등록 페이지(/postings/new)로 이동하는 버튼 구현 */}

                    </SubActions.Left>
                  </SubActions>
                  <PostingList.Header />
                  <PostingList.Content onClickEdit={this.onClickEdit} />
                  <PostingList.ActionButton />
                </PostingList>
              </Container>
            </Grid>
          </Grid>
        </Grid>
      </Box>
    );
  }
}

export default withRouter(PostingListPage);
