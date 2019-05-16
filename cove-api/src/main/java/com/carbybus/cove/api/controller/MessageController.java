package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.MessageApplication;
import com.carbybus.cove.domain.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消息控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"消息"})
@Validated
@RestController
@RequestMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController extends BaseController {
    private MessageApplication messageApp;

    @Autowired
    public MessageController(MessageApplication messageApp) {
        this.messageApp = messageApp;
    }

    @ApiOperation(value = "一条消息", notes = "获取一条消息")
    @GetMapping(value = "/one")
    public Message one() {
        return messageApp.getOne("name");
    }

    @ApiOperation(value = "所有消息", notes = "获取所有消息")
    @GetMapping(value = "/all")
    public List<Message> all() {
        return messageApp.listAll();
    }
}
