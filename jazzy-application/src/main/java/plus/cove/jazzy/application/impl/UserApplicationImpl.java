package plus.cove.jazzy.application.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.jazzy.application.UserApplication;
import plus.cove.jazzy.domain.entity.author.Author;
import plus.cove.jazzy.domain.exception.UserError;
import plus.cove.jazzy.domain.principal.UserPrincipal;
import plus.cove.jazzy.repository.AuthorRepository;

import java.util.Optional;

/**
 * 用户应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
@Slf4j
public class UserApplicationImpl implements UserApplication {
    private static final String CACHE_USER = "USER#1440";

    @Autowired
    AuthorRepository authorRep;

    @Override
    @Cacheable(value = CACHE_USER)
    public UserPrincipal findPrincipal(Long id) {
        Optional<Author> opAuthor = authorRep.selectById(id);
        if (opAuthor.isEmpty()) {
            log.error("the user does not found, the id is: {}", id);
            throw new BusinessException(UserError.INVALID_USER);
        }

        Author author = opAuthor.get();
        UserPrincipal user = UserPrincipal.init(
                author.getId(),
                author.getName());
        user.setUserAvatar(author.getAvatar());

        return user;
    }

    @Override
    @Cacheable(value = CACHE_USER)
    public void clearPrincipal(Long id) {
    }
}
