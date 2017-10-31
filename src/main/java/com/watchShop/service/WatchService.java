package com.watchShop.service;

import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;

import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 31.10.2017.
 */
public interface WatchService {
    Watch addNewWatch(Watch watch);

    boolean removeWatch(long id);

    List<Watch> getAllAvailableWatches();

    List<Watch> getAllWatchesByStatus(Status status);

    List<Watch> getAllWatches();

    Watch getWatchByTitle(String title);

    Watch getWatchById(long id);

    void updateWatch(long id, Map<String, String> mapWithProps);
}
