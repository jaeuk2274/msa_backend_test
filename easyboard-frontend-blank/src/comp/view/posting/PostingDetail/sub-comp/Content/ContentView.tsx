import React, { ContextType } from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box, Card, Modal, Typography } from '@nara.platform/react-ui';

import PostingDetailContext from '../../context/PostingDetailContext';
import { WithStyles, withStyles } from './style';


interface Props extends WithStyles {
  //
}

interface State {
  selectedImage: string;
}

@autobind
@observer
class ContentView extends ReactComponent<Props, State> {
  //
  static contextType = PostingDetailContext;

  context!: ContextType<typeof PostingDetailContext>;

  state: State = {
    selectedImage: '',
  };

  onOpen(image: string) {
    //
    this.setState({ selectedImage: image });
  }

  onClose() {
    //
    this.setState({ selectedImage: '' });
  }

  render() {
    //
    const { classes } = this.props;
    const { selectedImage } = this.state;
    const { posting } = this.context.postingDetail;

    return (
      <>
        <Card.Content className={classes.content}>
          {posting.deleted ? (
            <Typography variant="body1" color="textSecondary">
              삭제된 댓글입니다.
            </Typography>
          ) : (
            <Typography variant="body1" color="textPrimary">
              {posting.content}
            </Typography>
          )}
          {posting.base64AttachedImage && (
            <Box mt={2}>
              <Card.ActionArea onClick={() => this.onOpen(posting.base64AttachedImage)}>
                <Card.Media className={classes.media}>
                  <img src={posting.base64AttachedImage} alt="attached-image" />
                </Card.Media>
              </Card.ActionArea>
            </Box>
          )}
        </Card.Content>

        <Modal open={!!selectedImage} onClose={this.onClose}>
          <img src={selectedImage} alt="selected-image" style={{ objectFit: 'contain' }} />
        </Modal>
      </>
    );
  }
}

export default withStyles(ContentView);
