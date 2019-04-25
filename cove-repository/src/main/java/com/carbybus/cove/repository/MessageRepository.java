package com.carbybus.cove.repository;

import com.carbybus.cove.domain.entity.Message;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface MessageRepository extends BaseRepository<Message> {

}
