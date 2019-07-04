package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.UserApplication;
import com.carbybus.cove.application.utils.CacheConstants;
import com.carbybus.cove.domain.entity.user.Traveller;
import com.carbybus.cove.domain.exception.UserPrincipalError;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.repository.TravellerRepository;
import com.carbybus.infrastructure.caching.CacheUtils;
import com.carbybus.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TravellerRepository travellerRep;
    @Autowired
    private CacheUtils cacheUtils;

    @Override
    public UserPrincipal getPrincipal(Long id) {
        UserPrincipal user = cacheUtils.get(CacheConstants.USER_PRINCIPAL, id, UserPrincipal.class);
        if (user == null) {
            Traveller traveller = travellerRep.selectById(id);
            if (traveller == null) {
                log.error("the user does not found, the id is: {}", id);
                throw new BusinessException(UserPrincipalError.INVALID_USER);
            }

            user = UserPrincipal.init(
                    traveller.getId(),
                    traveller.getName());
            user.setUserAvatar(traveller.getAvatar());
        }

        return user;
    }

    @Override
    public void clearPrincipal(Long id) {
        cacheUtils.evict(CacheConstants.USER_PRINCIPAL, id);
    }
}
