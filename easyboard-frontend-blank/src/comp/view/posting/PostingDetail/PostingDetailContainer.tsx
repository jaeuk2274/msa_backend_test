
import React from 'react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { observer } from 'mobx-react';
import { Card } from '@nara.platform/react-ui';

import { Posting } from '~/comp/api';
import PostingDetailContext, { PostingDetailContextModel } from './context/PostingDetailContext';


interface Props {
  //
  posting: Posting;
  children: React.ReactNode;
}


@autobind
@observer
class PostingDetailContainer extends ReactComponent<Props> {
  //
  getContext(): PostingDetailContextModel {
    //
    const { posting } = this.props;

    return {
      postingDetail: {
        posting,
      },
    };
  }

  render() {
    //
    const { children } = this.props;

    return (
      <PostingDetailContext.Provider value={this.getContext()}>
        <Card>
          {children}
        </Card>
      </PostingDetailContext.Provider>
    );
  }
}

export default PostingDetailContainer;
