package plus.cove.jazzy.application.impl;

import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.jazzy.application.UserApplication;
import plus.cove.jazzy.domain.entity.user.Author;
import plus.cove.jazzy.domain.exception.UserError;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
@Slf4j
public class UserApplicationImpl implements UserApplication {
    private static final String USER_PRINCIPAL = "USER_PRINCIPAL#1440";

    @Autowired
    private AuthorRepository authorRep;

    @Override
    @Cacheable(value = USER_PRINCIPAL)
    public UserPrincipal findPrincipal(Long id) {
        Author author = authorRep.selectByPrimaryKey(id);
        if (author == null) {
            log.error("the user does not found, the id is: {}", id);
            throw new BusinessException(UserError.INVALID_USER);
        }

        UserPrincipal user = UserPrincipal.init(
                author.getId(),
                author.getName());
        user.setUserAvatar(author.getAvatar());

        return user;
    }

    @Override
    @Cacheable(value = USER_PRINCIPAL)
    public void clearPrincipal(Long id) {
    }
}
