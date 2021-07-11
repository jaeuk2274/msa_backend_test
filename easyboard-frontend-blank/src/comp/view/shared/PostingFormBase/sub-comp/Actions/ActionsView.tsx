import React from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Box, Button, Typography } from '@nara.platform/react-ui';
import ListIcon from '@material-ui/icons/List';
import RotateLeftIcon from '@material-ui/icons/RotateLeft';
import SendIcon from '@material-ui/icons/Send';


interface Props {
  content: string;
  maxLength: number;
  onClickList: (event: React.MouseEvent) => void;
  onSubmit: (event: React.MouseEvent) => void;
  onResetPosting: (event: React.MouseEvent) => void;
}


@autobind
@observer
class ActionsView extends ReactComponent<Props> {
  //
  render() {

    const { content, maxLength, onClickList, onSubmit, onResetPosting } = this.props;

    return (
      <div>
        <Box
          mt={1}
          justifyContent="space-between"
          alignItems="center"
          style={{
            display: 'inline-block',
            margin: '10px',
          }}
        >
          {/*className={classes.actions}*/}
          <Box>
            <Typography display="inline">{content.length}</Typography>
            <Typography display="inline">/</Typography>
            <Typography display="inline" color="textSecondary">{maxLength}</Typography>
          </Box>
        </Box>
        <Box
          mt={1}
          justifyContent="space-between"
          alignItems="center"
          style={{
            float: 'right',
            margin: '10px',
          }}
        >
          {/*className={classes.btnActions}*/}
          <Button
            id="listBtn"
            variant="contained"
            color="secondary"
            startIcon={<ListIcon />}
            className="listBtn" // cancelBtn
            onClick={onClickList}
          >
            List
          </Button>
          {' '}
          <Button
            variant="contained"
            startIcon={<RotateLeftIcon />}
            className="resetBtn"
            onClick={onResetPosting}
          >
            Reset
          </Button>
          {' '}
          <Button
            variant="contained"
            color="primary"
            endIcon={<SendIcon />}
            className="registrationBtn"
            onClick={onSubmit}
            disabled={content.length > maxLength}
          >
            Posting
          </Button>
        </Box>
      </div>
    );
  }
}

export default ActionsView;

