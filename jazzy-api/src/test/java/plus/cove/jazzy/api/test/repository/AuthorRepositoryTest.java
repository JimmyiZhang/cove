package plus.cove.jazzy.api.test.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import plus.cove.infrastructure.component.PageHelper;
import plus.cove.infrastructure.component.PageModel;
import plus.cove.infrastructure.component.PageResult;
import plus.cove.infrastructure.json.JsonUtils;
import plus.cove.jazzy.api.ApiApplication;
import plus.cove.jazzy.domain.view.AuthorListInput;
import plus.cove.jazzy.domain.view.AuthorListOutput;
import plus.cove.jazzy.repository.AuthorRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRep;
    @Autowired
    private PageHelper pageHelper;
    @Autowired
    private JsonUtils jsonUtils;

    private PageModel page = PageModel.DEFAULT;

    @Test
    public void selectPage() {
        AuthorListInput input = new AuthorListInput();
        List<AuthorListOutput> rows = authorRep.selectMany(input, page);
        PageResult output = pageHelper.toResult(rows);

        System.out.println("CLASS: ");
        System.out.println(output);
        System.out.println("JSON: ");
        System.out.println(jsonUtils.toJson(output));

        Assert.assertNotNull(output);
    }
}
