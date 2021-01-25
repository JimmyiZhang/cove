package plus.cove.jazzy.domain.entity.discovery;

/**
 * 壁纸
 *
 * @author jimmy.zhang
 * @since 1.0
 */

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
        if (StringUtils.isNotEmpty(image)) {
            this.image = image;
        } else {
            this.image = "https://api.dujin.org/bing/1920.php";
        }

        if (StringUtils.isNotEmpty(title)) {
            this.title = title;
        } else {
            this.title = "今日无事发生";
        }
    }
}
