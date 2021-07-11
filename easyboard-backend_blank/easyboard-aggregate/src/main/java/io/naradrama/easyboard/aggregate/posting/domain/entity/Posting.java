/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.domain.entity;

import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import io.naradrama.prologue.domain.NameValue;
import io.naradrama.prologue.domain.NameValueList;
import io.naradrama.prologue.domain.ddd.DomainAggregate;
import io.naradrama.prologue.domain.ddd.DomainEntity;
import io.naradrama.prologue.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class Posting extends DomainEntity implements DomainAggregate {
    //
    private String writerId;
    private String writerName;
    private String title;
    private String content;
    private String base64AttachedImage;
    private String boardId;
    private boolean important;
    private boolean deleted;
    private long time;

    public Posting(String id) {
        //
        super(id);
    }

    public Posting(PostingCdo postingCdo) {
        super();
        BeanUtils.copyProperties(postingCdo, this);
        this.important = false;
        this.deleted = false;
        this.time = System.currentTimeMillis();
    }

    public void markDeleted() {
        //
        this.deleted = true;
    }

    public void modifyValues(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "title":
                    this.title = value;
                    break;
                case "content":
                    this.content = value;
                    break;
                case "base64AttachedImage":
                    this.base64AttachedImage = value;
                    break;
                case "important":
                    this.important = Boolean.parseBoolean(value);
                    break;

                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static Posting fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Posting.class);
    }

    public static Posting sample() {
        //
        return new Posting(PostingCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
