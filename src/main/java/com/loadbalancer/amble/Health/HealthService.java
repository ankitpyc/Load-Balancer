package com.loadbalancer.amble.Health;

import com.loadbalancer.amble.Health.impl.HealthServiceImpl;
import com.loadbalancer.amble.ServerConfiguration.domain.ActiveNodeList;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


/*
TODO : This Health service will be invoked every time to perform Health Checks
TODO : This Service will be calling Health checkEndpoints for all servers registered
TODO : If a server misses the PING X number of times(Configurable) , we mark the server DOWN .
TODO : We maintain a list of inactive servers . There could be two issues why a server
       : we might get the response late
       : the server was actually down and it might be coming up later
 */

@Component
@AllArgsConstructor
public class HealthService implements HealthServiceImpl {

    @Override
    public void pingHeathChecks() {

    }

    /**
     *
     */
    @Override
    public void getAllServers() {

    }
}
