import * as PostingListTypes from './type';
import PostingListContainer from './PostingListContainer';
import Header from './sub-comp/PostingListHeader';
import Content from './sub-comp/PostingListContent';
import ActionButton from './sub-comp/PostingListActionButton';


type PostingListComponent = typeof PostingListContainer & {
  Header: typeof Header;
  Content: typeof Content;
  ActionButton: typeof ActionButton;
};

const PostingList = PostingListContainer as PostingListComponent;

PostingList.Header = Header;
PostingList.Content = Content;
PostingList.ActionButton = ActionButton;

export default PostingList;
export { PostingListTypes };
