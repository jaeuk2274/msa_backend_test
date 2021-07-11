import io.naradrama.easyboard.EasyboardBootApplication;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.domain.entity.sdo.PostingCdo;
import io.naradrama.easyboard.aggregate.posting.domain.logic.PostingLogic;
import io.naradrama.prologue.domain.NameValue;
import io.naradrama.prologue.domain.NameValueList;
import io.naradrama.prologue.domain.Offset;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest(classes = EasyboardBootApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicTest {
    //
    private String postingId;

    @Autowired
    private PostingLogic postingLogic;

    @Test
    @Rollback(value = false)
    public void registerTest() {
        PostingCdo postingCdo = PostingCdo.sample();
        postingCdo.setContent(postingCdo.getContent().substring(0, 100));
        postingCdo.setBase64AttachedImage(postingCdo.getBase64AttachedImage().substring(0, 100));
        this.postingId = postingLogic.registerPosting(postingCdo);

        Posting posting = postingLogic.findPosting(this.postingId);
        Assertions.assertNotNull(posting);
    }

    @Test
    public void modifyWithNameValueListTest() {
        String modifiedMessage = "modifiedByNameValueList";
        NameValueList nameValueList = new NameValueList().add(new NameValue("message", modifiedMessage));
        postingLogic.modifyPosting(this.postingId, nameValueList);

        Posting posting = postingLogic.findPosting(this.postingId);

        Assertions.assertEquals(modifiedMessage, posting.getContent());
    }

    @Test
    public void modifyWithObjectTest() {
        Posting origin = postingLogic.findPosting(this.postingId);
        String modifiedMessage = "modifiedByObject";
        origin.setContent(modifiedMessage);
        postingLogic.modifyPosting(origin);

        Posting posting = postingLogic.findPosting(this.postingId);

        Assertions.assertEquals(modifiedMessage, posting.getContent());
    }

    @Test
    public void removeTest() {
        postingLogic.removePosting(this.postingId);

        Assertions.assertThrows(Exception.class, () -> {
            postingLogic.findPosting(postingId);
        });
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class FindAllTestClazz {
        private List<String> postingIds;
        @BeforeAll
        public void registerPostings() {
            List<PostingCdo> cdos = new ArrayList<>();

            PostingCdo cdo_1 = PostingCdo.sample();
            cdo_1.setContent(cdo_1.getContent().substring(0, 100));
            cdo_1.setBase64AttachedImage(cdo_1.getBase64AttachedImage().substring(0, 100));
            cdos.add(cdo_1);

            PostingCdo cdo_2 = PostingCdo.sample();
            cdo_2.setContent(cdo_2.getContent().substring(0, 100));
            cdo_2.setBase64AttachedImage(cdo_2.getBase64AttachedImage().substring(0, 100));
            cdos.add(cdo_2);

            this.postingIds = postingLogic.registerPostings(cdos);
        }

        @Test
        public void findAllTest() {
            Offset offset_1 = Offset.newOne(0,1);
            List<Posting> postingsAsOne = postingLogic.findAllPosting(offset_1);
            Assertions.assertEquals(1, postingsAsOne.size());

            Offset offset_2 = Offset.newOne(0,2);
            List<Posting> postingsAsTwo = postingLogic.findAllPosting(offset_2);
            Assertions.assertEquals(2, postingsAsTwo.size());
        }

        @AfterAll
        public void resetData() {
            this.postingIds.forEach(id -> postingLogic.removePosting(id));
        }
    }

}
