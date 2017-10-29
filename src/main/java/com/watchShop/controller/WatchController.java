package com.watchShop.controller;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@RestController("/v1/watch")
public class WatchController {

    @Autowired
    WatchRepository watchRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllWatches() {
        return watchRepository.getWatchesByStatus(Status.AVAILABLE).toString();
    }
}
