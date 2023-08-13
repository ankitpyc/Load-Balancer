package com.loadbalancer.amble;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class APIServiceDetails {
    String key;
    String port;
    List<String> backendHosts;
}
