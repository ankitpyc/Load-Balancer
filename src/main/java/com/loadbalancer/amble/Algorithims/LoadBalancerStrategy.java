package com.loadbalancer.amble.Algorithims;

import com.loadbalancer.amble.Connections.TCPConnection;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

public interface LoadBalancerStrategy {

    public ServerDetails balanceRequest(TCPConnection connection);

    public void addServers(ServerDetails serverDetails);

    public void removeServers(ServerDetails serverDetails);

}
