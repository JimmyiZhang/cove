package plus.cove.jazzy.domain.entity.story;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;
import plus.cove.jazzy.domain.entity.coordinate.Coordinate;
import plus.cove.jazzy.domain.principal.UserPrincipal;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 照片
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Story extends DefaultEntity {
    private String name;
    private String image;
    private String subject;
    private String description;
    private Coordinate location;
    private LocalDateTime takeTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long userId;

    public void buildId() {
        this.valueOf();
    }

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Story create(UserPrincipal user) {
        Story story = new Story();
        story.valueOf();

        LocalDateTime now = LocalDateTime.now();
        story.createTime = now;
        story.updateTime = now;
        story.userId = user.getUserId();
        story.subject = StrUtil.EMPTY;
        story.description = StrUtil.EMPTY;
        return story;
    }

    /**
     * 从文件创建故事
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static Story fromFile(UserPrincipal user) {
        Story story = Story.create(user);
        story.userId = user.getUserId();
        story.subject = StrUtil.EMPTY;
        return story;
    }
}
