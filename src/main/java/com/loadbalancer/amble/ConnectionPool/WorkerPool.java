package com.loadbalancer.amble.ConnectionPool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerPool {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private ExecutorService executorService;

    @PostConstruct
    public void createWorkerPool(){
        executorService = Executors.newFixedThreadPool(10);
        logger.info("Initialized Connection Pool");
    }

    public ExecutorService getWorkerService(){
        return executorService;
    }

}
