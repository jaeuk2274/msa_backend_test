import React from 'react';
import { Box, Button, Grid } from '@material-ui/core';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { NextRouter, withRouter } from 'next/router';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';


interface Props {
  router: NextRouter;
}

interface InjectedProps {
}

@autobind
class LayoutSidebarView extends ReactComponent<Props, {}, InjectedProps> {

  routeToHome() {
    //
    // TODO: home 페이지로 이동

  }

  routeToBoard() {
    //
    // TODO: 게시판 페이지로 이동

  }

  render() {
    return (
      <Box>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <Button fullWidth onClick={this.routeToHome}>
              <HomeOutlinedIcon />
            </Button>
          </Grid>
          <Grid item xs={6}>
            <Button fullWidth>
              <SettingsOutlinedIcon />
            </Button>
          </Grid>
        </Grid>
        <Button onClick={this.routeToBoard} fullWidth className="boardButton">
          <b><h3>Board</h3></b>
        </Button>
      </Box>
    );
  }
}

export default withRouter(LayoutSidebarView);
