package com.watchShop.service;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import com.watchShop.exception.GenericEngineException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@Service
public class WatchServiceImpl implements WatchService {
    @Autowired
    WatchRepository watchRepository;

    @Override
    @Transactional
    public Watch addNewWatch(Watch watch) throws GenericEngineException {
        Watch save = watchRepository.save(watch);
        if(save != null) {
            return save;
        } else {
            throw new GenericEngineException("Entity was not saved");
        }
    }

    @Override
    @Transactional
    public boolean removeWatch(long id) throws GenericEngineException {
        try {
            Watch watch = watchRepository.getById(id);
            watchRepository.delete(watch);
            return true;
        } catch (Exception e) {
            throw new GenericEngineException("Requested entity was not found", e);
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
    public Watch getWatchByTitle(String title) throws GenericEngineException {
        Watch watchByTitle = watchRepository.getWatchByTitle(title);
        if(watchByTitle != null) {
            return watchByTitle;
        } else {
            throw new GenericEngineException("Requested entity was not found");
        }
    }

    @Override
    public Watch getWatchById(long id) throws GenericEngineException {
        Watch byId = watchRepository.getById(id);
        if(byId != null) {
            return byId;
        } else {
            throw new GenericEngineException("Requested entity was not found");
        }
    }

    @Override
    @Transactional
    public void updateWatch(long id, Map<String, String> mapWithProps) throws GenericEngineException {
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

    @Override
    public List<Watch> addNewWatches(List<Watch> watches) throws GenericEngineException {
        ArrayList<Watch> result = new ArrayList<>();
        watches.forEach(watch -> {
            Watch save = watchRepository.save(watch);
            result.add(save);
        });
        if (result != null) {
            return result;
        } else {
            throw new GenericEngineException("List of watches was not saved");
        }
    }
}
