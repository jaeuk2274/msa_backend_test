/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard.aggregate.posting.store.maria.jpo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import io.naradrama.prologue.store.jpa.DomainEntityJpo;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import org.springframework.beans.BeanUtils;
import io.naradrama.prologue.util.json.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "POSTING")
public class PostingJpo extends DomainEntityJpo {
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

    public PostingJpo(Posting posting) {
        //
        super(posting);
        BeanUtils.copyProperties(posting, this);
    }

    public Posting toDomain() {
        //
        Posting posting = new Posting(getId());
        BeanUtils.copyProperties(this, posting);
        return posting;
    }

    public static List<Posting> toDomains(List<PostingJpo> postingJpos) {
        //
        return postingJpos.stream().map(PostingJpo::toDomain).collect(Collectors.toList());
    }

    public String toString() {
        //
        return toJson();
    }

    public static PostingJpo sample() {
        //
        return new PostingJpo(Posting.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}
