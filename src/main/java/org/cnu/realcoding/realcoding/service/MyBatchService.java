package org.cnu.realcoding.realcoding.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@Slf4j
public class MyBatchService {
    @Autowired
    AsyncService asyncService;
    int counter = 0;

    @Scheduled(/*flexDelay?*/)
    public void count() {
        log.info("count: {}", counter++);
        if (counter < 10) {
            asyncService.increaseCounter();
        }
    }
}
