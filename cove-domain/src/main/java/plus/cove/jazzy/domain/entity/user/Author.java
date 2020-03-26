package plus.cove.jazzy.domain.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import plus.cove.infrastructure.component.impl.DefaultTimeEntity;

/**
 * 用户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "author")
public class Author extends DefaultTimeEntity {
    private String name;
    private String avatar;
    private String status;
    private UserGender gender;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Author create(String name) {
        Author author = new Author();
        author.valueOf();
        author.name = name;
        author.status = StringUtils.EMPTY;
        author.avatar = StringUtils.EMPTY;
        author.gender = UserGender.NONE;

        return author;
    }
}
