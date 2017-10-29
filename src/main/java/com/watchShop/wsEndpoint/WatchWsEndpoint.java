package com.watchShop.wsEndpoint;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import localhost._8080.ws.watchesshop.GetAllAvailableWatchesByStatusResponse;
import localhost._8080.ws.watchesshop.GetAllWatchesByStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleksandr Ryzhkov on 29.10.2017.
 */
@Endpoint
public class WatchWsEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/ws/watchesShop";

    @Autowired
    private WatchRepository watchRepository;

    public WatchWsEndpoint() {
    }


    /**
     * GetAllAvailableWatchesByStatusResponse and GetAllWatchesByStatusRequest will be generated in runtime
     * based on WSDL (watches.xsd)
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getWatchesByStatus")
    @ResponsePayload
    public GetAllAvailableWatchesByStatusResponse getCountry(@RequestPayload GetAllWatchesByStatusRequest request) {
        GetAllAvailableWatchesByStatusResponse response = new GetAllAvailableWatchesByStatusResponse();
        List<Watch> watches = watchRepository.getWatchesByStatus(Status.valueOf(request.getStatus().toString()));

        List<localhost._8080.ws.watchesshop.Watch> responseCollection = watches.stream().map(watch -> {
            localhost._8080.ws.watchesshop.Watch newWatch = new localhost._8080.ws.watchesshop.Watch();
            newWatch.setId(watch.getId());
            newWatch.setIsDigital(watch.isDigital());
            newWatch.setManufacturer(watch.getManufacturer());
            newWatch.setTitle(watch.getTitle());
            return newWatch;
        }).collect(Collectors.toList());

        response.getWatchesCollection().addAll(responseCollection);
        return response;
    }
}
