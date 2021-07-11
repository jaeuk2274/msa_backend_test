
import { PostingStateKeeper, PostingsStateKeeper } from './keeper';


export const store = {
  posting: {
    postingStateKeeper: PostingStateKeeper.instance,
    postingsStateKeeper: PostingsStateKeeper.instance,
  },
};

export {
  PostingStateKeeper,
  PostingsStateKeeper,
};
