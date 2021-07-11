
import React from 'react';
import { Posting } from '~/comp/api';


export type PostingDetailContextModel = {
  postingDetail: {
    posting: Posting;
  };
};

const PostingDetailContext = React.createContext<PostingDetailContextModel>({
  postingDetail: {
    posting: {} as Posting,
  },
});

export default PostingDetailContext;
