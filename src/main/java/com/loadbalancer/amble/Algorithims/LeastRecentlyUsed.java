package com.loadbalancer.amble.Algorithims;

import com.loadbalancer.amble.Connections.TCPConnection;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

public class LeastRecentlyUsed implements LoadBalancerStrategy{
    /**
     * @param connection
     */
    @Override
    public ServerDetails balanceRequest(TCPConnection connection) {
        return null;
    }

    /**
     * @param serverDetails
     */
    @Override
    public void addServers(ServerDetails serverDetails) {

    }

    /**
     * @param serverDetails
     */
    @Override
    public void removeServers(ServerDetails serverDetails) {

    }
}
