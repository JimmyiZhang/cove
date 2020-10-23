package plus.cove.infrastructure.system;

/**
 * 系统运行环境
 *
 * @author jimmy.zhang
 * @since 1.0
 */
public enum SystemRunTime {
    /**
     * 开发环境
     * 开发人员使用，版本变化很大
     *
     */
    DEVELOPMENT,

    /**
     * 仿真环境，兼顾测试环境和验收环境
     * 给测试人员使用，版本相对稳定
     */
    SIMULATION,

    /**
     * 生产环境
     * 面对外部环境，版本相当稳定
     */
    PRODUCTION;
}
