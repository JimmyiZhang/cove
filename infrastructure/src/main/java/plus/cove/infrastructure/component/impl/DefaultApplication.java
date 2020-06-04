package plus.cove.infrastructure.component.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.component.BaseApplication;
import plus.cove.infrastructure.component.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult save(T entity) {
        repository.insert(entity);

        return ActionResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult saveBatch(Collection<T> entityList) {
        if (CollectionUtils.isEmpty(entityList) || entityList.size() > MAX_BATCH_SIZE) {
            throw new IllegalArgumentException("超过批量操作上限");
        }

        for (T t : entityList) {
            repository.insert(t);
        }

        return ActionResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult removeById(Serializable id) {
        repository.deleteById(id);
        return ActionResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult updateById(T entity) {
        repository.updateById(entity);
        return ActionResult.success();
    }

    @Override
    public T getById(Serializable id) {
        return repository.selectById(id);
    }
}
