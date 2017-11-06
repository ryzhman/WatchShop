package com.watchShop.client;

import com.watchShop.exception.GenericEngineException;
import com.watchShop.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by Oleksandr Ryzhkov on 06.11.2017.
 */
@Component
public class WatchClient {
    @Value("${systemProperty.dataServer}")
    private String URL_SERVER_TO_GET_DATA_FROM;

    private WatchService watchService;

    @Autowired
    public WatchClient(WatchService watchService) {
        this.watchService = watchService;
    }

    public boolean fetchDataFromThirdPartyServer() throws GenericEngineException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String rawRequestResult = restTemplate.getForObject(URL_SERVER_TO_GET_DATA_FROM, String.class);
            //todo parse data
            //todo store data


            return true;
        } catch (Exception e) {
            throw new GenericEngineException("Could not fetch and store data from the third party server");
        }
    }
}
