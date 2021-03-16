package plus.cove.jazzy.domain.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 作者列表
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
public class AuthorListInput {
    @NotEmpty(message = "名称不能为空")
    @Size(min = 2, max = 16, message = "名称2-16个字符")
    private String name;
}
