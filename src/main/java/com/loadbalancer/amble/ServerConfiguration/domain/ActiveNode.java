package com.loadbalancer.amble.ServerConfiguration.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActiveNode {
    private ServerDetails serverDetails;
    private ActiveNode next,prev;
    public ActiveNode(ServerDetails serverDetails){
        this.serverDetails = serverDetails;
        this.next = null;
        this.prev = null;
    }
}
