package plus.cove.jazzy.api.test.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.entity.author.Author;
import plus.cove.jazzy.repository.AuthorRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRep;

    @Test
    public void selectById() {
        Optional<Author> opAuthor = authorRep.selectById(1L);
        if (opAuthor.isPresent()) {
            System.out.println(opAuthor.get());
        }

        Assert.assertTrue(opAuthor.isEmpty());
    }
}
