import React from 'react';
import { observer } from 'mobx-react';
import { autobind, DramaException, ReactComponent, ServiceInjector } from '@nara.drama/prologue';
import { PostingStateKeeper } from '../../../state';
import PostingFormView from './view/PostingFormView';


interface Props {
  boardId: string;
  userId: string;
  writerName: string;
  postingId?: string;
  rows?: number;
  onClickList?: (event: React.MouseEvent) => void;
  onSuccess?: () => void;
  onFail?: () => void;
}

interface InjectedProps {
  //
  postingStateKeeper: PostingStateKeeper;
}

@autobind
@observer
class PostingFormContainer extends ReactComponent<Props, {}, InjectedProps> {
  //
  static defaultProps = {
    postingId: '',
    config: {},
    rows: 5,
    userId: '',
    writerName: '',
    onClickList: () => {},
    onSuccess: () => {},
    onFail: () => {},
  };

  componentDidMount() {
    //
    this.init();
  }

  componentDidUpdate(prevProps: Readonly<Props>) {
    //
    const { boardId: prevBoardId } = prevProps;
    const { boardId } = this.props;

    if (prevBoardId !== boardId) {
      this.init();
    }
  }

  async init() {
    //
    await this.initPosting();
  }

  async initPosting() {
    // TODO: postingStateKeeper의 posting 값 설정
    //  1. props의 postingId가 있을 경우, postingId에 대한 posting 검색 후 설정
    //  2. props의 postingId가 undefined일 경우, boardId, userId, writerName으로 posting 설정

  }

  onChange(event: React.ChangeEvent<HTMLInputElement>) {
    //
    // TODO: 게시글의 property 수정

  }

  async onResetPosting(event: React.MouseEvent) {
    //
    await this.initPosting();
  }

  async onSubmit() {
    //
    const { onSuccess, onFail } = this.propsWithDefault;
    const { postingStateKeeper } = this.injected;
    const { posting } = postingStateKeeper;


    if (!posting) {
      throw new DramaException('PostingForm', 'Posting should not be null.');
    }

    // TODO: Posting 저장 후 결과에 따라 onSuccess/onFail 실행


    await this.initPosting();
  }

  render() {
    const { rows, onClickList } = this.propsWithDefault;
    const { postingStateKeeper } = this.injected;
    const { posting } = postingStateKeeper;

    if (!posting) {
      return null;
    }

    return (
      <PostingFormView
        title={posting.title}
        content={posting.content}
        maxLength={140}
        rows={rows}
        onChange={this.onChange}
        onClickList={onClickList}
        onResetPosting={this.onResetPosting}
        onSubmit={this.onSubmit}
      />
    );
  }
}

export default ServiceInjector.withContext(
  PostingStateKeeper
)(PostingFormContainer);
