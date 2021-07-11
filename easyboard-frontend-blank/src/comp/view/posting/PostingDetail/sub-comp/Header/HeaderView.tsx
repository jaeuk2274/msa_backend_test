import React, { ContextType } from 'react';
import { observer } from 'mobx-react';
import moment from 'moment';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Avatar, Box, Card, Link, Typography } from '@nara.platform/react-ui';
import { AccessTime } from '@material-ui/icons';

import { Posting } from '~/comp/api';
import PostingDetailContext from '../../context/PostingDetailContext';


interface Props {
  //
  anonymous?: boolean;
  renderAction?: (posting: Posting) => React.ReactNode;
}

@autobind
@observer
class HeaderView extends ReactComponent<Props> {
  //
  static defaultProps = {
    anonymous: false,
    renderAction: () => null,
  };

  static contextType = PostingDetailContext;

  context!: ContextType<typeof PostingDetailContext>;

  render() {
    //
    const { anonymous, renderAction } = this.propsWithDefault;
    const { posting } = this.context.postingDetail;

    console.log(posting);

    return (
      <Card.Header
        disableTypography
        avatar={<Avatar alt={posting.writerName} src={!anonymous ? posting.writerId : ''} />}
        title={(
          <div>
            <Link color="textPrimary" variant="h6">
              {!anonymous ? posting.title : 'anonymous'}
            </Link>
            {/*{'  '}*/}
            {/*<Link color="textPrimary" variant="h6">*/}
            {/*  {!anonymous ? posting.writerName : 'anonymous'}*/}
            {/*</Link>*/}
          </div>
        )}
        subheader={(
          <Box display="flex" alignItems="center" color="text.secondary">
            <Box display="flex" pr={1}>
              <AccessTime fontSize="small" />
            </Box>
            <Typography variant="body2">
              {moment(posting.time).fromNow()}
            </Typography>
          </Box>
        )}
        action={renderAction(posting)}
      />
    );
  }
}

export default HeaderView;
