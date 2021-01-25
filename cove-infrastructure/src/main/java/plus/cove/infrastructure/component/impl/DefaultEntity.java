package plus.cove.infrastructure.component.impl;


import plus.cove.infrastructure.component.BaseEntity;
import plus.cove.infrastructure.generator.KeyGeneratorBuilder;

import javax.persistence.Id;

/**
 * 实体基类
 * <p>
 * 领域类，表示业务对象
 * 1. 提供基本属性
 * 2. 提供基础方法，valueOf用于生成id等基本属性
 * 3. 可进行扩展，带创建属性，带更新属性
 * init表示初始化一个实例，使用系统默认值
 * create表示创建一个新实例，使用实体默认值
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public abstract class DefaultEntity implements BaseEntity<Long> {
    @Id
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 初始化实体
     * <p>
     * 统一管理id生成
     * 方便更换id生成器
     *
     * @author jimmy.zhang
     * @since 1.0
     */
    protected void valueOf() {
        long key = KeyGeneratorBuilder.INSTANCE.build();
        this.setId(key);
    }
}
