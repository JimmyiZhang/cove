package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.coordinate.CoordinateAround;
import plus.cove.jazzy.domain.entity.story.Story;

import java.util.List;

/**
 * 照片仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
public interface StoryRepository extends MybatisRepository<Story, Long> {
    /**
     * 插入数据
     * 原生支持
     *
     * @param entity 实体数据
     * @return
     * @author jimmy.zhang
     * @date 2019-05-21
     */
    void insertStory(Story entity);

    /**
     * 根据名字获取
     * 按照创建时间返回指定条数的数据
     *
     * @param name 查询名称，支持模糊查询
     * @param size 返回条数
     * @return
     * @author jimmy.zhang
     * @date 2019-05-28
     */
    List<Story> selectByName(@Param("name") String name, @Param("size") Integer size);

    /**
     * 根据位置查询
     * 按照范围查询指定条数的数据
     *
     * @param around 坐标范围
     * @param size   返回条数
     * @return
     * @author jimmy.zhang
     * @date 2019-05-28
     */
    List<Story> selectByNear(@Param("around") CoordinateAround around, @Param("size") Integer size);

    /**
     * 根据主题查询
     *
     * @param subjects 主题
     * @param size     返回条数
     * @return
     * @author jimmy.zhang
     * @date 2019-05-28
     */
    List<Story> selectBySubject(@Param("subjects") String[] subjects, @Param("size") Integer size);
}
