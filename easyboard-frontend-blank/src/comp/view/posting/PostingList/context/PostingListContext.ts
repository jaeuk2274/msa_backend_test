
import React from 'react';
import { SortDirection } from '@nara.drama/prologue';


export type PostingListContextModel = {
  postingList: {
    userId: string;
    sourceEntityId: string;
    sourceEntityName: string;
    init: (sortDirection?: SortDirection) => void;
    findMorePostings: () => void;
    writerName: string;
  };
};

const PostingListContext = React.createContext<PostingListContextModel>({
  postingList: {
    userId: '',
    sourceEntityId: '',
    sourceEntityName: '',
    init: () => {},
    findMorePostings: () => {},
    writerName: '',
  },
});

export default PostingListContext;
