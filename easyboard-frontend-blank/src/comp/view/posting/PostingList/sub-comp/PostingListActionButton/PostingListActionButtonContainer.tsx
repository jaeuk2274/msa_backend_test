import React, { ContextType } from 'react';
import { autobind, ReactComponent, ServiceInjector } from '@nara.drama/prologue';
import { observer } from 'mobx-react';
import { Box, Button } from '@nara.platform/react-ui';

import { PostingsStateKeeper } from '~/comp/state';
import PostingListContext from '../../context/PostingListContext';


interface Props {
  //
  onClick?: (event: React.MouseEvent) => void;
}

interface InjectedProps {
  //
  postingsStateKeeper: PostingsStateKeeper;
}

@autobind
@observer
class PostingListActionButtonContainer extends ReactComponent<Props, {}, InjectedProps> {
  //
  static defaultProps = {
    onClick: () => {},
  };

  static contextType = PostingListContext;

  context!: ContextType<typeof PostingListContext>;

  onClick(event: React.MouseEvent) {
    //
    const { onClick } = this.propsWithDefault;
    const { postingList } = this.context;

    postingList.findMorePostings();
    onClick(event);
  }

  render() {
    const { postingsStateKeeper } = this.injected;
    const { offset } = postingsStateKeeper;
    const hasMorePostings = offset.totalCount > (offset.offset + offset.limit);

    return hasMorePostings && (
      <Box my={4} textAlign="center">
        <Button variant="text" onClick={this.onClick}>Show more Postings...</Button>
      </Box>
    );
  }
}

export default ServiceInjector.useContext(
  PostingsStateKeeper
)(PostingListActionButtonContainer);
