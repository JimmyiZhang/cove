package plus.cove.infrastructure.mybatis.mapper;

import io.mybatis.provider.defaults.GenericEntityClassFinder;

import javax.persistence.Table;

/**
 * 支持识别带有 @javax.persistence.Table 的实体类
 *
 * @author liuzh
 */
public class JpaEntityClassFinder extends GenericEntityClassFinder {
    @Override
    public boolean isEntityClass(Class<?> clazz) {
        return clazz.isAnnotationPresent(Table.class);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 100;
    }
}
