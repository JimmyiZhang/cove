package plus.cove.jazzy.application;


import plus.cove.jazzy.domain.entity.discovery.DiscoveryPlace;
import plus.cove.jazzy.domain.entity.discovery.DiscoveryWallpaper;
import plus.cove.jazzy.domain.view.DiscoveryInput;

/**
 * 地图应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface DiscoveryApplication {
    /**
     * 根据输入生成探索点
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-25
     */
    DiscoveryPlace generatePlace(DiscoveryInput input);

    /**
     * 获取墙纸
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    DiscoveryWallpaper fetchWallpaper();
}
