package com.loadbalancer.amble.utils;

import com.loadbalancer.amble.ServerConfiguration.domain.ActiveNode;
import com.loadbalancer.amble.ServerConfiguration.domain.ActiveNodeList;
import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;

import java.util.Map;
import java.util.Objects;

public class DiscoveryUtils {
    public static String getServerId(ServerDetails serverDetails) {
        StringBuilder sb = new StringBuilder();
        return sb.append(serverDetails.getHostIp()).append(serverDetails.getPort()).toString();
    }

    public static ActiveNode addServerToList(ActiveNodeList activeNodeList, ServerDetails serverDetails){
        ActiveNode node = ActiveNode.builder().serverDetails(serverDetails).build();
        if(activeNodeList.getStart().equals(null)){
            activeNodeList.setStart(node);
            activeNodeList.setEnd(node);
        }else{
            ActiveNode startNode = activeNodeList.getStart();
            startNode.setPrev(node);
            node.setNext(startNode);
            activeNodeList.setStart(node);
        }
        return node;
    }

    public static void removeServerFromList(ActiveNodeList activeNodeList,Map<String,ActiveNode> inactiveServers,ActiveNode activeNode) {
        //Add it to the List of Active Servers
        inactiveServers.put(activeNode.getServerDetails().getServerId(),activeNode);
        //Change the Start of the Node
        if(activeNode.equals(activeNodeList.getStart())){
            activeNodeList.setStart(activeNode.getNext());
        }
        if(Objects.nonNull(activeNode.getNext())){
            activeNode.getNext().setPrev(activeNode.getPrev());
        }
        if(Objects.nonNull(activeNode.getPrev())){
            activeNode.getPrev().setNext(activeNode.getNext());
        }

    }
}
