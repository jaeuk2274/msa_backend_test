import {
  NameValueList,
  fromDomain,
  DomainEntity,
  makeExtendedObservable,
} from '@nara.drama/prologue';
import moment from 'moment';


@fromDomain
class Posting extends DomainEntity {
  writerId: string;
  writerName: string;
  title: string;
  content: string;
  base64AttachedImage: string;
  boardId: string;
  important: boolean;
  deleted: boolean;
  time: number;

  /* for ui */
  expanded: boolean = false;
  editing: boolean = false;

  constructor(
    writerId: string,
    writerName: string,
    title: string,
    content: string,
    base64AttachedImage: string,
    boardId: string,
    important: boolean,
    deleted: boolean,
    time: number,
  ) {
    super();
    this.writerId = writerId;
    this.writerName = writerName;
    this.title = title;
    this.content = content;
    this.base64AttachedImage = base64AttachedImage;
    this.boardId = boardId;
    this.important = important;
    this.deleted = deleted;
    this.time = time;

    makeExtendedObservable(this);
  }

  static fromDomain(domain: Posting): Posting {
    const posting = new Posting(
      domain.writerId,
      domain.writerName,
      domain.title,
      domain.content,
      domain.base64AttachedImage,
      domain.boardId,
      domain.important,
      domain.deleted,
      domain.time,
    );

    posting.setDomainEntity(domain);
    return posting;
  }

  static fromDomains(domains: Posting[]): Posting[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static asNameValues(posting: Posting) {
    //
    return NameValueList.fromModel(Posting, posting, {
      title: String,
      content: String,
      base64AttachedImage: String,
      important: String,
      // embeddedSubPostings: JSON,
    });
  }

  get displayTime() {
    //
    return moment(this.time).format('YYYY-MM-DD');
  }
}

export default Posting;
