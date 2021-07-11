
import React from 'react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { Button, List, Menu, MenuTypes, Typography } from '@nara.platform/react-ui';
import { Delete, Edit, MoreVert } from '@material-ui/icons';


interface Props {
  //
  postingId: string;
}

@autobind
class PostingActionView extends ReactComponent<Props> {
  //
  render() {
    //
    const { postingId } = this.props;

    return (
      <Menu
        trigger={
          <Button.Icon size="small">
            <MoreVert />
          </Button.Icon>
        }
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
      >
        <Menu.Item
          onClick={(event: React.MouseEvent, params: MenuTypes.ClickItemParams) => {
            params.close();
          }}
        >
          <List.ItemIcon><Edit /></List.ItemIcon>
          <Typography>Edit</Typography>
        </Menu.Item>
        <Menu.Item
          onClick={(event: React.MouseEvent, params: MenuTypes.ClickItemParams) => {
            params.close();
          }}
        >
          <List.ItemIcon><Delete /></List.ItemIcon>
          <Typography>Remove</Typography>
        </Menu.Item>
      </Menu>
    );
  }
}

export default PostingActionView;
