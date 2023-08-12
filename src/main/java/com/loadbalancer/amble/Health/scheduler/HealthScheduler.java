package com.loadbalancer.amble.Health.scheduler;


import com.loadbalancer.amble.Health.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthScheduler {

    @Autowired
    private HealthService healthService;

    @Scheduled
    public void runHealthJob(){
        //TODO : Add Implentation Here
    }

}
