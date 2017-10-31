package com.watchShop.service;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@Service
@Transactional
public class WatchServiceImpl implements WatchService {
    @Autowired
    WatchRepository watchRepository;

    @Override
    public Watch addNewWatch(Watch watch) {
        return watchRepository.save(watch);
    }

    @Override
    public boolean removeWatch(long id) {
        try {
            Watch watch = watchRepository.getById(id);
            watchRepository.delete(watch);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Watch> getAllAvailableWatches() {
        return watchRepository.getWatchesByStatus(Status.AVAILABLE);
    }

    @Override
    public List<Watch> getAllWatchesByStatus(Status status){
        return watchRepository.getWatchesByStatus(status);
    }

    @Override
    public List<Watch> getAllWatches() {
        return watchRepository.findAll();
    }

    @Override
    public Watch getWatchByTitle(String title) {
        return watchRepository.getWatchByTitle(title);
    }

    @Override
    public Watch getWatchById(long id) {
        return watchRepository.getById(id);
    }

    @Override
    public void updateWatch(long id, Map<String, String> mapWithProps) {
        Watch watchToUpdate = getWatchById(id);

        for (Map.Entry<String, String> entry : mapWithProps.entrySet()) {
            if (StringUtils.isNoneEmpty(entry.getValue())) {
                switch (entry.getKey()) {
                    case "title":
                        watchToUpdate.setTitle(entry.getValue());
                        break;
                    case "manufacturer":
                        watchToUpdate.setManufacturer(entry.getValue());
                        break;
                    case "isDigital":
                        watchToUpdate.setDigital(Boolean.valueOf(entry.getValue()));
                        break;
                    case "status":
                        watchToUpdate.setStatus(Status.valueOf(entry.getValue()));
                        break;
                }
            }
        }
        watchRepository.save(watchToUpdate);
    }
}
