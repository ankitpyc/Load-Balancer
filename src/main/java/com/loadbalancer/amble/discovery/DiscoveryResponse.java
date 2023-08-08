package com.loadbalancer.amble.discovery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DiscoveryResponse {
    private  DiscoverStatus discoverStatus;
}
