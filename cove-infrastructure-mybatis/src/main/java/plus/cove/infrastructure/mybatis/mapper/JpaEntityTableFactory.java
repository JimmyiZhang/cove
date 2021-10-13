package plus.cove.infrastructure.mybatis.mapper;

import io.mybatis.provider.EntityTable;
import io.mybatis.provider.EntityTableFactory;

import javax.persistence.Table;

/**
 * 通过 SPI 工厂扩展 EntityColumn 和 EntityTable
 *
 * @author liuzh
 */
public class JpaEntityTableFactory implements EntityTableFactory {
    @Override
    public EntityTable createEntityTable(Class<?> entityClass, Chain chain) {
        EntityTable entityTable = chain.createEntityTable(entityClass);
        if (entityClass.isAnnotationPresent(Table.class)) {
            Table table = entityClass.getAnnotation(Table.class);
            if (entityTable == null) {
                entityTable = EntityTable.of(entityClass);
            }
            if (!table.name().isEmpty()) {
                entityTable.table(table.name());
            }
        }
        return entityTable;
    }

    @Override
    public int getOrder() {
        return EntityTableFactory.super.getOrder() + 100;
    }
}
