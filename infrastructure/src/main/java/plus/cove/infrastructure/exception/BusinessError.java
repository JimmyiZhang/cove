package plus.cove.infrastructure.exception;

/**
 * <p>
 * 业务错误枚举
 * </p>
 * 所有异常枚举接口
 * 100-199 本地验证错误，包括
 *  100-109 验证错误 ValidatorError
 *  110-119 Jwt错误 JwtError
 *  120-129 转换错误 ConverterError
 *  130-139 Cache错误 CacheError
 *
 * 200-299 消息类
 *
 * 300-399 文件和网络错误
 *  300-309 文件错误 FileError
 *  310-319 网络错误 NetworkError
 *
 * 400-599 服务错误 ServiceError
 *  401, 404, 500
 *
 * @author jimmy.zhang
 * @date 2019-02-28
 */
public interface BusinessError {
    /**
     * 获取描述
     *
     * @return 错误描述
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    String getMessage();

    /**
     * 获取编码
     *
     * @return 错误编码
     * @author jimmy.zhang
     * @date 2019-03-29
     */
    int getCode();
}
