package com.loadbalancer.amble.KeepAlive;

import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServerConfiguration {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String, Map<String, ServerDetails>> serverConfigDetails;


    ServerConfiguration() {
        serverConfigDetails = new HashMap<>();
        logger.info("Initialized Empty Central Server Repository");
    }

    public void addServerDetails(String API_SERVICE, ServerDetails serverDetails) {
        if (serverConfigDetails.containsKey(API_SERVICE))
            serverConfigDetails.put(API_SERVICE, new HashMap<String, ServerDetails>());
        serverConfigDetails.get(API_SERVICE).put(serverDetails.getServerId(), serverDetails);
    }

    public void removeServerDetails(String API_SERVICE, String serverId) {
        synchronized (ServerConfiguration.class) {
            serverConfigDetails.get(API_SERVICE).remove(serverId);
        }
    }

}
