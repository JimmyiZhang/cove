package com.carbybus.cove.repository;


import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 照片仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface PhotoRepository extends BaseRepository<Photo> {
    /**
     * 插入数据
     * 原生支持
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-21
     */
    @Override
    int insert(Photo entity);
}
