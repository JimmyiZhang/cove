package plus.cove.infrastructure.logger;

import lombok.Data;

/**
 * 前端日志
 *
 * @author jimmy.zhang
 * @since 1.0
 */
@Data
public class LoggerFront {
    /**
     * 事件部分：时间
     */
    private String eventTime;

    /**
     * 事件部分：类型
     * 支持ERROR
     */
    private String eventType;

    /**
     * 事件部分：地址
     */
    private String eventUrl;

    /**
     * 事件部分：消息
     */
    private String eventInfo;

    /**
     * 事件部分：文件
     */
    private String eventFile;

    /**
     * 事件部分：堆栈
     */
    private String eventStack;

    /**
     * 事件部分：行号
     */
    private String eventRow;

    /**
     * 事件部分：列号
     */
    private String eventColumn;

    /**
     * 应用部分：名称
     */
    private String appName;

    /**
     * 应用部分：实例
     */
    private String appInstance;

    /**
     * 设备部分：名称
     * 包括PC, MOBILE, WECHAT
     */
    private String device;

    /**
     * 设备部分：系统及版本
     */
    private String deviceSystem;

    /**
     * 设备部分：浏览器名称及版本
     */
    private String browserName;

    /**
     * 设备部分：浏览器引擎及版本
     */
    private String browserEngine;

    /**
     * 转为json
     *
     * @return
     */
    public String toJson() {
        StringBuilder sbJson = new StringBuilder();
        
        return sbJson.toString();
    }
}
