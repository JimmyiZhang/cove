package com.carbybus.cove.api.controller;

import com.carbybus.cove.api.component.BaseController;
import com.carbybus.cove.application.DiscoveryApplication;
import com.carbybus.cove.domain.entity.discovery.DiscoveryPlace;
import com.carbybus.cove.domain.view.DiscoveryInput;
import com.carbybus.infrastructure.component.ActionResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 探索控制器
 *
 * @author jimmy.zhang
 * @date 2019-02-26
 */
@Api(tags = {"探索游戏"})
@Validated
@RestController
@RequestMapping(value = "/discovery")
public class DiscoveryController extends BaseController {
    @Autowired
    private DiscoveryApplication discoveryApp;

    @RequestMapping(value = "place", method = RequestMethod.GET)
    public ActionResult getPlace(@Valid DiscoveryInput input) {
        DiscoveryPlace output = discoveryApp.generatePlace(input);
        return success(output);
    }
}
