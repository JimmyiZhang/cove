package plus.cove.infrastructure.mask;

import lombok.Data;
import org.springframework.util.StringUtils;
import plus.cove.infrastructure.utils.StringHelper;

/**
 * 覆盖格式化读取
 * <p>
 * 包括标识符
 * 内容，参数
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
public class MaskReader {
    /**
     * 格式内容
     */
    private String masker;

    /**
     * 原始数据
     */
    private String rawData;

    /**
     * 开始索引
     */
    private Integer beginIndex;

    /**
     * 结束索引
     */
    private Integer endIndex;

    /**
     * 格式化
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public String format(String mask) {
        if (StringUtils.isEmpty(mask)) {
            return rawData;
        }

        if (mask.contains(this.masker)) {
            return StringHelper.mask(rawData, beginIndex, endIndex - beginIndex, '*');
        }

        return rawData;
    }
}
