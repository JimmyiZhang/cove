package com.carbybus.infrastructure.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * 时间分片算法
 *
 * @author jimmy.zhang
 * @date 2019-04-25
 */
public final class YearShardingAlgorithm implements PreciseShardingAlgorithm<Timestamp> {
    /**
     * 无参的构造器
     * 需要提供
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-25
     */
    public YearShardingAlgorithm() {
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Timestamp> preciseShardingValue) {
        // 按年分片
        for (String each : collection) {
            String year = String.valueOf(preciseShardingValue.getValue().toLocalDateTime().getYear());

            if (each.endsWith(year)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
