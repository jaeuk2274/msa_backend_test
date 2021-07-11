import io.naradrama.easyboard.EasyboardBootApplication;
import io.naradrama.easyboard.aggregate.posting.domain.entity.Posting;
import io.naradrama.easyboard.aggregate.posting.store.PostingStore;
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
public class StoreTest {
    //
    private Posting posting;

    @Autowired
    private PostingStore postingStore;

    @BeforeAll
    public void initPosting() {
        Posting sample = Posting.sample();
        sample.setContent(sample.getContent().substring(0, 100));
        sample.setBase64AttachedImage(sample.getBase64AttachedImage().substring(0, 100));
        this.posting = sample;
    }

    @Test
    @Rollback(value = false)
    public void createTest() {
        postingStore.create(this.posting);

        retrieveTest();
    }

    public void retrieveTest() {
        Posting foundPosting = postingStore.retrieve(this.posting.getId());

        Assertions.assertNotNull(foundPosting);
        Assertions.assertEquals(this.posting, foundPosting);
    }

    @Test
    public void updateTest() {
        String modifiedMessage = "modified";
        this.posting.setContent(modifiedMessage);

        postingStore.update(this.posting);
        Posting modifiedPosting = postingStore.retrieve(this.posting.getId());
        Assertions.assertEquals(modifiedMessage, modifiedPosting.getContent());
    }

    @Test
    public void deleteTest() {
        postingStore.delete(this.posting.getId());

        Assertions.assertNull(postingStore.retrieve(this.posting.getId()));
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class RetrieveAllTestClazz {
        private List<Posting> postingList;
        private final String boardId = "board1";

        @BeforeAll
        public void initPostings() {
            this.postingList = new ArrayList<>();
            Posting sample1 = Posting.sample();
            sample1.setContent(sample1.getContent().substring(0, 100));
            sample1.setBase64AttachedImage(sample1.getBase64AttachedImage().substring(0, 100));
            sample1.setBoardId(boardId);
            this.postingList.add(sample1);
            Posting sample2 = Posting.sample();
            sample2.setContent(sample2.getContent().substring(0, 100));
            sample2.setBase64AttachedImage(sample2.getBase64AttachedImage().substring(0, 100));
            sample2.setBoardId(boardId);
            this.postingList.add(sample2);

            this.postingList.forEach(posting -> postingStore.create(posting));
        }

        @Test
        public void retrieveAllTest() {
            //
            Offset offset_1 = Offset.newOne(0,1);
            List<Posting> postingsAsOne = postingStore.retrieveAll(offset_1);
            Assertions.assertEquals(1, postingsAsOne.size());

            Offset offset_2 = Offset.newOne(0,2);
            List<Posting> postingsAsTwo = postingStore.retrieveAll(offset_2);
            Assertions.assertEquals(2, postingsAsTwo.size());
        }

//        @Test
//        public void retrieveAllByFeedBackIdTest() {
//            //
//            List<Posting> postings = postingStore.retrieveAllByFeedbackId(boardId);
//            Assertions.assertEquals(2, postings.size());
//        }
//
//        @Test
//        public void deleteAllByFeedBackIdTest() {
//            //
//            List<Posting> postings = postingStore.deleteByFeedbackId(boardId);
//            Assertions.assertEquals(2, postings.size());
//            List<Posting> postingsExpectedEmpty = postingStore.retrieveAllByFeedbackId(boardId);
//            Assertions.assertEquals(0, postingsExpectedEmpty.size());
//        }
    }
}
