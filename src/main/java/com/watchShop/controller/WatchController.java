package com.watchShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import com.watchShop.service.WatchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@RestController
@RequestMapping("/v1/watch")
public class WatchController {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;

    @RequestMapping(method = RequestMethod.GET)
    public String getWatchByTitle(@RequestParam("title") String title) throws JsonProcessingException {
        Watch watchByTitle = watchService.getWatchByTitle(title);
        return mapper.writeValueAsString(watchByTitle);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewWatch(@RequestBody JsonNode newWatch) throws JsonProcessingException {
        Watch watch = mapper.treeToValue(newWatch, Watch.class);
        if (watchService.addNewWatch(watch) != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeWatch(@PathVariable("id") long id) {
        watchService.removeWatch(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateWatch(@PathVariable("id") long id, @RequestBody JsonNode detailsToUpdate) throws JsonProcessingException {
        Map<String, String> mapWithProps = mapper.treeToValue(detailsToUpdate, Map.class);

        watchService.updateWatch(id, mapWithProps);
    }
}

