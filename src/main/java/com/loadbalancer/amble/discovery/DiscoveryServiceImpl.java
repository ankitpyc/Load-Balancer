package com.loadbalancer.amble.discovery;

import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

public interface DiscoveryServiceImpl {

    //Method to register
    public DiscoveryResponse registerServer(ServerDetails serverDetails);

    //Method to de-register
    public void removeServerFromRegistry(String serverId);

    public void removeServerFromRegistry(ServerDetails serverDetails);

}
