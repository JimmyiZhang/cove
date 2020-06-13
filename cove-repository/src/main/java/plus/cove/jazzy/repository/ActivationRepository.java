package plus.cove.jazzy.repository;


import org.springframework.stereotype.Repository;
import plus.cove.jazzy.domain.entity.user.Activation;
import tk.mybatis.mapper.common.Mapper;

/**
 * 验证码仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface ActivationRepository extends Mapper<Activation> {
    /**
     * 根据用户码获取信息
     *
     * @param userCode 用户码
     * @return 用户验证码
     * @author jimmy.zhang
     * @since 1.1
     */
    Activation selectByCode(String userCode);

    Activation selectByAuth(String authCode);
}
