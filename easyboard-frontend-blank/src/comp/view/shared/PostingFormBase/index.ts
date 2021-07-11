
import PostingFormBaseView from './PostingFormBaseView';
import Posting from './sub-comp/Posting';
import Actions from './sub-comp/Actions';


type PostingFormBaseComponent = typeof PostingFormBaseView & {
  Posting: typeof Posting;
  Actions: typeof Actions;
};

const PostingFormBase = PostingFormBaseView as PostingFormBaseComponent;

PostingFormBase.Posting = Posting;
PostingFormBase.Actions = Actions;

export default PostingFormBase;
