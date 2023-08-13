package com.loadbalancer.amble;


import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@Builder
@ConfigurationProperties(value = "api")
public class LoadBalancerConfigurations {

    List<APIServiceDetails> service;

    String loadBalancerStrategy;


}
