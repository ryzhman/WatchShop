package com.watchShop.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchShop.entity.Status;
import com.watchShop.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Oleksandr Ryzhkov on 01.11.2017.
 */
@RestController
@RequestMapping("/v1/watches")
public class WatchListController {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private WatchService watchService;

    @RequestMapping(method = RequestMethod.GET)
    public JsonNode getAllWatches(@RequestParam("status") Optional<String> status) {
        Status status1 = Status.valueOf(status.orElse("AVAILABLE"));
        return mapper.valueToTree(watchService.getAllWatchesByStatus(status1));
    }
}
