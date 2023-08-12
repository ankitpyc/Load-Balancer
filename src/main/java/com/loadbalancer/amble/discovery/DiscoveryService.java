package com.loadbalancer.amble.discovery;

import com.loadbalancer.amble.ServerConfiguration.domain.ActiveNode;
import com.loadbalancer.amble.ServerConfiguration.domain.ActiveNodeList;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;
import com.loadbalancer.amble.utils.DiscoveryUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DiscoveryService implements DiscoveryServiceImpl {

    private final Map<String, ActiveNode> activeServerMap, inActiveServerMap;
    private final ActiveNodeList activeNodeList;


    public DiscoveryService() {
        this.activeServerMap = new ConcurrentHashMap<>();
        this.inActiveServerMap = new ConcurrentHashMap<>();
        this.activeNodeList = new ActiveNodeList();
    }


    /**
     * @param serverDetails sd
     * @return
     */
    @Override
    public DiscoveryResponse registerServer(ServerDetails serverDetails) {
        String serverId = DiscoveryUtils.getServerId(serverDetails);
        serverDetails.setServerId(serverId);
        if(activeServerMap.containsKey(serverId)){
            return DiscoveryResponse.builder().discoverStatus(DiscoverStatus.REDUNDANT).build();
        }
        ActiveNode activeNode = DiscoveryUtils.addServerToList(activeNodeList,serverDetails);
        activeServerMap.put(serverId,activeNode);
        return DiscoveryResponse.builder().discoverStatus(DiscoverStatus.SUCCESS).build();
    }

    /**
     * @param serverId
     */

    @Override
    public void removeServerFromRegistry(String serverId) {
        if(activeServerMap.containsKey(serverId)){
            ActiveNode activeNode = activeServerMap.get(serverId);
            DiscoveryUtils.removeServerFromList(activeNodeList,inActiveServerMap,activeNode);
        }
    }

    /**
     * @param serverDetails
     */
    @Override
    public void removeServerFromRegistry(ServerDetails serverDetails) {
        String serverId = DiscoveryUtils.getServerId(serverDetails);
        removeServerFromRegistry(serverId);
    }
}
