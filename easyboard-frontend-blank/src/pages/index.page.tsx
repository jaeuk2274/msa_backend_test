import React from 'react';
import { NextRouter, withRouter } from 'next/router';
import { autobind, injectFromName, ReactComponent } from '@nara.drama/prologue';


interface Props {
  router: NextRouter;
}

interface InjectedProps {
  //
}

@autobind
class IndexPage extends ReactComponent<Props, {}, InjectedProps> {
  //
  componentDidMount() {
    //

    // TODO: home 페이지로 이동

  }

  render() {
    //
    return (
      <div>
        <strong>Welcome to Board Posting Project! </strong>
      </div>
    );
  }
}

export default withRouter(IndexPage);
