package plus.cove.jazzy.repository;


import org.apache.ibatis.annotations.Mapper;
import plus.cove.infrastructure.mybatis.MybatisRepository;
import plus.cove.jazzy.domain.entity.global.Activation;


/**
 * 验证码仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Mapper
public interface GlobalActivationRepository extends MybatisRepository<Activation, Long> {
    /**
     * 根据用户码获取信息
     *
     * @param userCode 用户码
     * @return 用户验证码
     * @author jimmy.zhang
     * @since 1.1
     */
    Activation selectByCode(String userCode);

    /**
     * 根据验证码获取
     *
     * @param authCode 验证码
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    Activation selectByAuth(String authCode);
}
