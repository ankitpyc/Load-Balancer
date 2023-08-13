package com.loadbalancer.amble.Event;

import com.loadbalancer.amble.ServerConfiguration.domain.ServerDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerHostEvent {

    private ServerEvent event;
    private ServerDetails serverDetails;
    
}
