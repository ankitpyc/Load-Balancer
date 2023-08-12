package com.loadbalancer.amble.ServerConfiguration.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Builder
@Data
public class ServerDetails {
    String serverId;
    String hostIp;
    Integer port;
    Boolean isActive;
    Optional<AtomicInteger> cyclesMissed;
}
