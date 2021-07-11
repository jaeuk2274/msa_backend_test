
import PostingDetailContainer from './PostingDetailContainer';
import Header from './sub-comp/Header';
import Content from './sub-comp/Content';


type PostingDetailComponent = typeof PostingDetailContainer & {
  Header: typeof Header;
  Content: typeof Content;
};

const PostingDetail = PostingDetailContainer as PostingDetailComponent;

PostingDetail.Header = Header;
PostingDetail.Content = Content;

export default PostingDetail;
