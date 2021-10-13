package plus.cove.infrastructure.mybatis.mapper;

import io.mybatis.provider.EntityColumn;
import io.mybatis.provider.EntityColumnFactory;
import io.mybatis.provider.EntityField;
import io.mybatis.provider.EntityTable;
import plus.cove.infrastructure.utils.StringHelper;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 通过 SPI 工厂扩展 EntityColumn 和 EntityTable
 * <p>
 * 修改获取列逻辑，没有@Transient都处理
 *
 * @author liuzh
 * @author jimmy.zhang
 */
public class JpaEntityColumnFactory implements EntityColumnFactory {
    @Override
    public Optional<List<EntityColumn>> createEntityColumn(EntityTable entityTable, EntityField field, Chain chain) {
        Optional<List<EntityColumn>> optionalEntityColumns = chain.createEntityColumn(entityTable, field);
        if (field.isAnnotationPresent(Transient.class)) {
            return Optional.empty();
        } else if (!optionalEntityColumns.isPresent()) {
            optionalEntityColumns = Optional.of(Arrays.asList(EntityColumn.of(field).column(field.getName())));
        }

        if (optionalEntityColumns.isPresent()) {
            List<EntityColumn> entityColumns = optionalEntityColumns.get();
            for (EntityColumn entityColumn : entityColumns) {
                EntityField entityField = entityColumn.field();
                // 主键
                if (!entityColumn.id()) {
                    entityColumn.id(entityField.isAnnotationPresent(Id.class));
                }

                // 列名，存在Column以此为准
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.name();
                    if (!columnName.isEmpty()) {
                        entityColumn.column(columnName);
                    }
                    entityColumn.insertable(column.insertable()).updatable(column.updatable());
                    if (column.scale() != 0) {
                        entityColumn.numericScale(String.valueOf(column.scale()));
                    }
                } else {
                    // 处理列名，caseToUnderline
                    String columnName = StringHelper.caseToUnderline(field.getName());
                    entityColumn.column(columnName);
                }

                // 只能默认空 ASC，或者写 ASC 或 DESC，不能写多个列
                if (field.isAnnotationPresent(OrderBy.class)) {
                    OrderBy orderBy = field.getAnnotation(OrderBy.class);
                    if (orderBy.value().isEmpty()) {
                        entityColumn.orderBy("ASC");
                    } else {
                        entityColumn.orderBy(orderBy.value());
                    }
                }
            }
        }
        return optionalEntityColumns;
    }

    @Override
    public int getOrder() {
        return EntityColumnFactory.super.getOrder() + 100;
    }
}
