import React from 'react';
import {
  autobind,
  Offset,
  OffsetElementList,
  ReactComponent,
  ServiceInjector,
  SortDirection,
} from '@nara.drama/prologue';
import { observer } from 'mobx-react';
import { PostingsStateKeeper, PostingStateKeeper } from '../../../state';
import { Posting } from '~/comp/api';
import PostingListContext, { PostingListContextModel } from './context/PostingListContext';



interface Props {
  //
  children: React.ReactNode;
  sourceEntityId: string;
  sourceEntityName: string;
  userId: string;
  writerName: string;
  limit?: number;
  showDeleted?: boolean;
  onInit?: (totalCount: number) => void;
}

interface InjectedProps {
  //
  postingsStateKeeper: PostingsStateKeeper;
  postingStateKeeper: PostingStateKeeper;
}

@autobind
@observer
class PostingListContainer extends ReactComponent<Props, {}, InjectedProps> {
  //
  static defaultProps = {
    limit: 15,
    showDeleted: false,
    writerName: '',
    onInit: () => {},
  };

  componentDidMount() {
    //
    this.init();
  }

  componentDidUpdate(prevProps: Readonly<Props>) {
    //
    const { sourceEntityId: prevSourceEntityId } = prevProps;
    const { sourceEntityId } = this.props;

    if (prevSourceEntityId !== sourceEntityId) {
      this.init();
    }
  }

  async init(sortDirection?: SortDirection) {
    //
    const { onInit } = this.propsWithDefault;
    const { sourceEntityId } = this.props;
    const { limit, showDeleted } = this.propsWithDefault;
    const { postingsStateKeeper } = this.injected;
    const { offset } = postingsStateKeeper;

    const targetLimit = offset.limit || limit;
    const targetOffset = new Offset(0, targetLimit);

    targetOffset.sortDirection = sortDirection || SortDirection.Descending;
    targetOffset.sortingField = 'time';

    // TODO: 특정 조건을 만족할 경우, 게시글 목록에서 삭제된 글까지 확인할 수 있도록 허용하는 코드 구현

  }

  getContext(): PostingListContextModel {
    //
    const { sourceEntityId, sourceEntityName, userId, writerName } = this.props;

    return {
      postingList: {
        sourceEntityId,
        sourceEntityName,
        userId,
        writerName,
        init: this.init,
        findMorePostings: this.findMorePostings,
      },
    };
  }

  findMorePostings() {
    //
    const { sourceEntityId } = this.props;
    const { postingsStateKeeper } = this.injected;
    const { offset } = postingsStateKeeper;

    const newOffset = { ...offset };

    newOffset.limit = offset.limit + offset.limit;

    postingsStateKeeper.findPostingsByBoardId(sourceEntityId, newOffset);
  }

  renderChildren() {
    //
    const { children } = this.props;
    let targetChildren = children;

    if (typeof children === 'function') {
      targetChildren = children({
        init: this.init,
      });
    }

    return targetChildren;
  }

  render() {
    //
    return (
      <PostingListContext.Provider value={this.getContext()}>
        {this.renderChildren()}
      </PostingListContext.Provider>
    );
  }
}

export default ServiceInjector.withContext(
  PostingsStateKeeper,
  PostingStateKeeper
)(PostingListContainer);
