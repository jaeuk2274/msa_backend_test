import React from 'react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box, Grid, Paper } from '@nara.platform/react-ui';
import KeyboardBackspaceIcon from '@material-ui/icons/KeyboardBackspace';
import { NextRouter, withRouter } from 'next/router';
import { LayoutSidebar } from '~/comp/view';


interface Props {
  router: NextRouter;
}

interface InjectedProps {
}

@autobind
class IndexPage extends ReactComponent<Props, {}, InjectedProps> {
  render() {
    return (
      <Box>
        <Grid container spacing={2} className="root">
          <Grid item xs={2}>
            <LayoutSidebar />
          </Grid>
          <Grid item xs={10}>
            <Grid item xs={12}>
              <Paper elevation={0} variant="outlined" className="title" square><b>Welcome</b></Paper>
            </Grid>
            <Grid item xs={12} className="postArea">
              <table>
                <tr>
                  <td><KeyboardBackspaceIcon fontSize="large" /></td>
                  <td><h3>게시판을 선택해주세요</h3></td>
                </tr>
              </table>
            </Grid>
          </Grid>
        </Grid>
      </Box>
    );
  }
}

export default withRouter(IndexPage);
