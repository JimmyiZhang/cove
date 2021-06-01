package plus.cove.jazzy.domain.entity.logging;

import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import java.time.LocalDateTime;

/**
 * 日志
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Logging extends DefaultEntity {
    /**
     * 操作路由
     */
    private String router;
    /**
     * 类别
     * 业务自由区分
     */
    private String category;
    /**
     * 简介
     */
    private String brief;
    /**
     * 详情
     */
    private String detail;
    /**
     * 操作对象id
     * 会员使用
     */
    private Long targetId;
    /**
     * 操作对象
     */
    private String targetName;
    /**
     * 操作人
     */
    private Long operateId;
    /**
     * 操作人
     */
    private String operateName;
    /**
     * 操作时间
     */
    private LocalDateTime operateTime;
    /**
     * 操作设备
     */
    private String operateDevice;
    /**
     * 操作地址
     */
    private String operateSource;
    /**
     * 请求版本
     */
    private String operateVersion;
}
