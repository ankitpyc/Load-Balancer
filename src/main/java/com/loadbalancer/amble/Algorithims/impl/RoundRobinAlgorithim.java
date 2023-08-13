package com.loadbalancer.amble.Algorithims.impl;

import com.loadbalancer.amble.Algorithims.LoadBalancerStrategy;
import com.loadbalancer.amble.Connections.TCPConnection;
import com.loadbalancer.amble.Event.ServerEvent;
import com.loadbalancer.amble.Event.ServerHostEvent;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class RoundRobinAlgorithim implements LoadBalancerStrategy, Observer {

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


    /**
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ServerHostEvent){
            ServerHostEvent serverHostEvent = (ServerHostEvent) arg;
            if(serverHostEvent.getEvent().equals(ServerEvent.SERVER_HOST_ADDED)){
                addServers(serverHostEvent.getServerDetails());
            }
            if(serverHostEvent.getEvent().equals(ServerEvent.SERVER_HOST_REMOVED)){
                removeServers(serverHostEvent.getServerDetails());
            }
        }
    }
}
