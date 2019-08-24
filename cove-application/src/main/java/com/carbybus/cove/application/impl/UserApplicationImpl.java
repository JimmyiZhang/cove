package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.UserApplication;
import com.carbybus.cove.domain.entity.user.Traveller;
import com.carbybus.cove.domain.exception.UserPrincipalError;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.repository.TravellerRepository;
import com.carbybus.infrastructure.exception.BusinessException;
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
    private TravellerRepository travellerRep;

    @Override
    @Cacheable(value = USER_PRINCIPAL)
    public UserPrincipal getPrincipal(Long id) {
        Traveller traveller = travellerRep.selectById(id);
        if (traveller == null) {
            log.error("the user does not found, the id is: {}", id);
            throw new BusinessException(UserPrincipalError.INVALID_USER);
        }

        UserPrincipal user = UserPrincipal.init(
                traveller.getId(),
                traveller.getName());
        user.setUserAvatar(traveller.getAvatar());

        return user;
    }

    @Override
    @Cacheable(value = USER_PRINCIPAL)
    public void clearPrincipal(Long id) {
    }
}
