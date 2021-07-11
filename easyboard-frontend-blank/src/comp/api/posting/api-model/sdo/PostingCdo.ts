import Posting from '../Posting';


class PostingCdo {
  writerId: string;
  writerName: string;
  title: string;
  content: string;
  base64AttachedImage: string;
  boardId: string;

  constructor(writerId: string, writerName: string, title: string, content: string, base64AttachedImage: string, boardId: string) {
    this.writerId = writerId;
    this.writerName = writerName;
    this.title = title;
    this.content = content;
    this.base64AttachedImage = base64AttachedImage;
    this.boardId = boardId;
  }

  static fromModel(domain: Posting): PostingCdo {
    return new PostingCdo(
      domain.writerId,
      domain.writerName,
      domain.title,
      domain.content,
      domain.base64AttachedImage,
      domain.boardId,
    );
  }

}

export default PostingCdo;
