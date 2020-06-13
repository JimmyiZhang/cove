package plus.cove.jazzy.domain.entity.journey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import plus.cove.infrastructure.component.impl.DefaultEntity;
import plus.cove.infrastructure.file.FileMetadata;
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
    private String url;
    private String subject;
    private String description;
    private Coordinate location;
    private LocalDateTime takeTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long ownerId;

    public void buildId(){
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
        story.setCreateTime(now);
        story.setUpdateTime(now);
        story.setOwnerId(user.getUserId());
        story.setSubject(StringUtils.EMPTY);
        story.setDescription(StringUtils.EMPTY);
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
    public static Story fromFile(UserPrincipal user, FileMetadata file) {
        Story story = Story.create(user);
        story.setName(file.getName());
        story.setUrl(file.getUrl());
        story.setTakeTime(file.getToken());
        story.setOwnerId(user.getUserId());
        story.setSubject(StringUtils.EMPTY);

        String description = String.format("from: %s %s", file.getMake(), file.getModel());
        story.setDescription(description);

        Coordinate coordinate = new Coordinate(file.getLatitude(), file.getLongitude());
        story.setLocation(coordinate);
        return story;
    }
}
