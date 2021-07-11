
import React from 'react';
import { observer } from 'mobx-react';
import { autobind, ReactComponent } from '@nara.drama/prologue';
import { TextField } from '@nara.platform/react-ui';


interface Props {
  title: string;
  content: string;
  maxLength: number;
  rows: number;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}


@autobind
@observer
class PostingView extends ReactComponent<Props> {
  //
  render() {
    const { title, content, maxLength, rows, onChange } = this.props;

    return (
      <div>
        <TextField
          placeholder="Title"
          fullWidth
          name="title"
          value={title || ''}
          onChange={onChange}
        />
        <br /><br />
        <TextField
          placeholder="Leave a posting"
          fullWidth
          multiline={rows !== 1}
          rows={rows}
          rowsMax={rows}
          name="content"
          value={content || ''}
          error={content.length > maxLength}
          onChange={onChange}
        />
      </div>

    );
  }
}

export default PostingView;
