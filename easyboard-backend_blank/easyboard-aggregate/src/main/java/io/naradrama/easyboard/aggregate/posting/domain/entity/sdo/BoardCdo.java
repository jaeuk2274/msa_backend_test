package io.naradrama.easyboard.aggregate.posting.domain.entity.sdo;

import io.naradrama.prologue.domain.IdName;
import io.naradrama.prologue.util.json.JsonSerializable;
import io.naradrama.prologue.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCdo implements JsonSerializable {
    //
    private String writerId;
    private String writerName;
    private String title;
    private String postId;

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static BoardCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, BoardCdo.class);
    }

    public static BoardCdo sample() {
        //
        IdName writer = IdName.sample();
        String writerId = writer.getId();
        String writerName = writer.getName();
        String title = "Board is posting.";
        String postId = UUID.randomUUID().toString();

        BoardCdo sample = new BoardCdo(writerId, writerName, title, postId);

        return sample;
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
