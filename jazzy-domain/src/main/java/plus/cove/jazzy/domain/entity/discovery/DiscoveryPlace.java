package plus.cove.jazzy.domain.entity.discovery;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

/**
 * 探索输出
 * <p>
 * 根据游戏难度获取目标点的坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
public class DiscoveryPlace {
    /**
     * 目标坐标纬度
     */
    private Double latitude;

    /**
     * 目标坐标经度
     */
    private Double longitude;

    /**
     * 纬度最大偏移量
     */
    private Double latitudeLimit;
    /**
     * 经度最大偏移量
     */
    private Double longitudeLimit;

    /**
     * 查看显示级别
     */
    private Integer viewZoom;
    /**
     * 探索显示级别
     */
    private Integer findZoom;

    /**
     * 根据难度创建探索点
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-25
     */
    public static DiscoveryPlace create(Double latitude, Double longitude, DiscoveryLevel level) {
        DiscoveryPlace dp = new DiscoveryPlace();
        dp.setFindZoom(level.getFind());
        dp.setViewZoom(level.getView());

        // 目标坐标
        Double ofLatitude = RandomUtils.nextDouble(latitude - level.getOffset() * level.getFactor(), latitude + level.getOffset() * level.getFactor());
        dp.setLatitude(ofLatitude);
        Double ofLongitude = RandomUtils.nextDouble(longitude - level.getOffset(), longitude + level.getOffset());
        dp.setLongitude(ofLongitude);

        // 限制偏移
        dp.setLatitudeLimit(level.getLimit() * level.getFactor());
        dp.setLongitudeLimit(level.getLimit());

        return dp;
    }
}
