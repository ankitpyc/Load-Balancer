package com.loadbalancer.amble.Algorithims;

import com.loadbalancer.amble.Connections.TCPConnection;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinAlgorithim implements LoadBalancerStrategy     {

    /** @param connection - will be used to initiate a connection
     *
     */
    
    private Queue<ServerDetails> serverDetailsQueue;
    
    RoundRobinAlgorithim() {
        serverDetailsQueue = new LinkedList<>();
    }

    /*
    * synchronized to get a request per server
    * */
    @Override
    public ServerDetails balanceRequest(TCPConnection connection) {
        ServerDetails serverDetails = null;
        synchronized (RoundRobinAlgorithim.class){
            serverDetails = serverDetailsQueue.poll();
            while (!serverDetails.getIsActive()){
                serverDetails = serverDetailsQueue.poll();
            }
            serverDetailsQueue.add(serverDetails);
        }
        return serverDetails;
    }

    /**
     * @param serverDetails 
     */
    @Override
    public void addServers(ServerDetails serverDetails) {
        serverDetailsQueue.add(serverDetails);
    }

    /**
     * @param serverDetails
     */
    @Override
    public void removeServers(ServerDetails serverDetails) {


    }


}
