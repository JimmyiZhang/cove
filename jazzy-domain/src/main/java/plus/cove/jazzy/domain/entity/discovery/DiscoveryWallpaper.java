package plus.cove.jazzy.domain.entity.discovery;

/**
 * 壁纸
 *
 * @author jimmy.zhang
 * @since 1.0
 */

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class DiscoveryWallpaper {
    /**
     * 地址
     */
    private String image;
    /**
     * 标题
     */
    private String title;

    public void with(String image, String title) {
        if (StrUtil.isNotEmpty(image)) {
            this.image = image;
        } else {
            this.image = "https://api.dujin.org/bing/1920.php";
        }

        if (StrUtil.isNotEmpty(title)) {
            this.title = title;
        } else {
            this.title = "今日无事发生";
        }
    }
}
