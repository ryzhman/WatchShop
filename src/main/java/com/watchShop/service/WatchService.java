package com.watchShop.service;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@Service
@Transactional
public class WatchService {
    @Autowired
    WatchRepository watchRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 11; i++) {
            Watch watch = new Watch("Watch" + i, "Manufacturer" + (i * i), i % 2 == 0, Status.AVAILABLE);
            watchRepository.save(watch);
        }
    }
}
