import React, { ContextType } from 'react';
import { autobind, ReactComponent, ServiceInjector, SortDirection } from '@nara.drama/prologue';
import { observer } from 'mobx-react';
import { FormControl, Radio, SubActions } from '@nara.platform/react-ui';

import { PostingsStateKeeper } from '~/comp/state';
import PostingListContext from '../../context/PostingListContext';


interface InjectedProps {
  //
  postingsStateKeeper: PostingsStateKeeper;
}

@autobind
@observer
class PostingListHeaderContainer extends ReactComponent<{}, {}, InjectedProps> {
  //
  static contextType = PostingListContext;

  context!: ContextType<typeof PostingListContext>;

  onChange(event: React.ChangeEvent<HTMLInputElement>) {
    //
    const { postingList } = this.context;
    const sortDirection = event.target.value as SortDirection;

    postingList.init(sortDirection);
  }

  render() {
    const { postingsStateKeeper } = this.injected;
    const { offset } = postingsStateKeeper;

    return (
      <SubActions>
        <SubActions.Right>
          <FormControl.ControlLabel
            label="NewData"
            control={
              <Radio
                className="base"
                name="radioGroup"
                value={SortDirection.Descending}
                checked={offset.sortDirection !== SortDirection.Ascending}
                onChange={this.onChange}
              />
            }
          />
          <FormControl.ControlLabel
            label="OldData"
            control={
              <Radio
                className="base"
                name="radioGroup"
                value={SortDirection.Ascending}
                checked={offset.sortDirection === SortDirection.Ascending}
                onChange={this.onChange}
              />
            }
          />
        </SubActions.Right>
      </SubActions>
    );
  }
}

export default ServiceInjector.useContext(
  PostingsStateKeeper
)(PostingListHeaderContainer);
