# Easyboard


## Domain Model

### Posting

```plantuml

hide circle
hide methods

skinparam classAttributeIconSize 0
skinparam linetype polyline
skinparam linetype ortho

entity Board <<DomainAggregate>> <<DomainEntity>> #FDC {
    config : PostingConfig
    title : String
    time : long
    sourceEntityId : String
    <i>*postings : List<Posting>
}

entity PostingConfig <<ValueObject>> #EFE {
    anonymous : boolean
    maxPostingCount : int
    maxPostingMessag : int
    maxSubPostingCou : int
    maxSubPostingMes : int
    embeddedSubC : boolean
    maxEmbeddedSubCo : int
    deletable : boolean
}

entity Posting <<DomainAggregate>> <<DomainEntity>> #FDC {
    writerId : String
    writerName : String
    message : String
    base64AttachedImage : String
    boardId : String
    important : boolean
    deleted : boolean
    time : long
    subPostingCount : int
    embeddedSubPostings : EmbeddedSubPostingList
    <i>*subPostings : private List<SubPosting>
}

entity SubPosting <<DomainEntity>> {
    writerId : String
    writerName : String
    message : String
    base64Atta : String
    postingId : String
    time : long
    deleted : boolean
}

entity EmbeddedSubPostingList <<ValueObject>> #EFE {
    embeddedSubPostings : List<EmbeddedSubPosting>
}

entity EmbeddedSubPosting <<ValueObject>> #EFE {
    writerId : String
    writerName : String
    message : String
    base64AttachedImage : String
    time : long
    deleted : boolean
}

PostingFeedback -down-> "n" Posting
PostingFeedback -right- PostingConfig
Posting -down-> "n" SubPosting
Posting -right- EmbeddedSubPostingList
EmbeddedSubPostingList -down-> "n" EmbeddedSubPosting

```

### Report

```plantuml

hide circle
hide methods

skinparam classAttributeIconSize 0
skinparam linetype polyline
skinparam linetype ortho

entity ReportFeedback <<DomainAggregate>> <<DomainEntity>> #FDC {
    config : ReportConfig
    title : String
    sourceEntityId : String
    time : long
    <i>*reports : List<Report>
}

entity ReportConfig <<ValueObject>> #EFE {
    anonymous : boolean
    maxReportCount : int
    maxReportMessageLength : int
}

entity Report <<DomainAggregate>> <<DomainEntity>> #FDC {
    writerId : String
    writerName : String
    title : String
    message : String
    capturedImageId : String
    fileBoxId : String
    boardId : String
    correctActions : List<CorrectAction>
    time : long
}

entity CorrectAction <<ValueObject>> #EFE {
    writerId : String
    writerName : String
    message : String
    time : long
}

ReportFeedback -right- ReportConfig
ReportFeedback -down-> "n" Report
Report -right-> "n" CorrectAction

```

### Review

```plantuml

hide circle
hide methods

skinparam classAttributeIconSize 0
skinparam linetype polyline
skinparam linetype ortho

entity ReviewFeedback <<DomainAggregate>> <<DomainEntity>> #FDC {
    config : ReviewConfig
    title : String
    sourceEntityId : String
    time : long
    <i>*reviewSummary : ReviewSummary
    <i>*reviews : List<Review>
}

entity ReviewConfig <<ValueObject>> #EFE {
    maxStarCount : int
    versionBased : boolean
    anonymous : boolean
    maxReviewCount : int
    maxReviewOpinionLength : int
}

entity ReviewSummary <<DomainEntity>> {
    versionBased : boolean
    maxStarCount : int
    boardId : String
    starCounts : List<IntPair>
    average : double
    reviewCount : int
    versionStarCountMap : Map<String, List>
    versionAverageMap : Map<String, Double>
    versionReviewCountMap : Map<String, Integer>
}

entity Review <<DomainAggregate>> <<DomainEntity>> #FDC {
    writerId : String
    writerName : String
    title : String
    opinion : String
    selectedStar : int
    version : String
    boardId : String
    time : long
    helpCountPair : IntPair
    <i>*helpPostings : List<HelpPosting>
}

entity HelpPosting <<DomainEntity>> {
    anonymous : boolean
    writerId : String
    helpful : boolean
    reviewId : String
    time : long
}

ReviewFeedback -right- ReviewConfig
ReviewFeedback -left- ReviewSummary
ReviewFeedback -down-> "n" Review
Review -right-> "n" HelpPosting

```
