import React from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box, Grid, Paper } from '@nara.platform/react-ui';

import { PostingFormBase } from '~/comp/view/shared';


interface Props {
  title: string;
  content: string;
  maxLength: number;
  rows: number;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onClickList: (event: React.MouseEvent) => void;
  onSubmit: (event: React.MouseEvent) => void;
  onResetPosting: (event: React.MouseEvent) => void;
}


@autobind
@observer
class PostingFormView extends ReactComponent<Props> {
  //
  render() {
    const {
      title, content, maxLength, rows, onChange, onClickList, onResetPosting, onSubmit,
    } = this.props;

    return (
      <PostingFormBase>
        <Grid item xs={12}>
          <Paper elevation={0} square className="subTitle"><b>Form</b></Paper>
        </Grid>
        <Box p={2}>
          <PostingFormBase.Posting
            title={title}
            content={content}
            maxLength={maxLength}
            rows={rows}
            onChange={onChange}
          />
        </Box>

        <PostingFormBase.Actions
          content={content}
          maxLength={maxLength}
          onClickList={onClickList}
          onResetPosting={onResetPosting}
          onSubmit={onSubmit}
        />
      </PostingFormBase>
    );
  }
}

export default PostingFormView;
