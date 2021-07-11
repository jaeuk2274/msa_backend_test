package io.naradrama.easyboard.aggregate.posting.domain.entity;
import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.BoardCdo;
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
public class Board extends DomainEntity implements DomainAggregate {
    //
    private String writerId;
    private String writerName;
    private String title;
    private boolean important;
    private boolean deleted;
    private long time;

    public Board(String id) {
        //
        super(id);
    }

    public Board(BoardCdo boardCdo) {
        super();
        BeanUtils.copyProperties(boardCdo, this);
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
        //w
        return toJson();
    }

    public static Board fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Board.class);
    }

    public static Board sample() {
        //
        return new Board(BoardCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
