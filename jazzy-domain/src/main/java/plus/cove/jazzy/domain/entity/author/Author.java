package plus.cove.jazzy.domain.entity.author;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultTimeEntity;
import plus.cove.jazzy.domain.entity.account.UserGender;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Entity
@Table(name = "author")
@EqualsAndHashCode(callSuper = true)
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
        author.status = StrUtil.EMPTY;
        author.avatar = StrUtil.EMPTY;
        author.gender = UserGender.NONE;

        return author;
    }
}
