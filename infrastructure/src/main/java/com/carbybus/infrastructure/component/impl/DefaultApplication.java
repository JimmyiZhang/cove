package com.carbybus.infrastructure.component.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.carbybus.infrastructure.component.BaseApplication;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础应用的实现
 *
 * @author jimmy.zhang
 * @date 2019-03-27
 */
public class DefaultApplication<M extends BaseRepository<T>, T> implements BaseApplication<T> {
    /**
     * 批量上限
     */
    private static final int MAX_BATCH_SIZE = 1024;

    /**
     * 本应用仓储
     */
    @Autowired
    protected M repository;

    /**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(T entity) {
        return retBool(repository.insert(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        if (CollectionUtils.isEmpty(entityList) || entityList.size() > MAX_BATCH_SIZE) {
            throw new IllegalArgumentException("超过批量操作上限");
        }

        int total = 0;
        for (T t : entityList) {
            int affected = repository.insert(t);
            total += affected;
        }

        return total == entityList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        return SqlHelper.delBool(repository.deleteById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        return SqlHelper.delBool(repository.delete(queryWrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return retBool(repository.updateById(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return retBool(repository.update(entity, updateWrapper));
    }

    @Override
    public T getById(Serializable id) {
        return repository.selectById(id);
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper) {
        return repository.selectOne(queryWrapper);
    }

    @Override
    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(repository.selectCount(queryWrapper));
    }

    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return repository.selectList(queryWrapper);
    }

    @Override
    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return repository.selectPage(page, queryWrapper);
    }
}
