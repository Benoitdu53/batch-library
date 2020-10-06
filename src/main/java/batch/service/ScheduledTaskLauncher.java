package batch.service;

import batch.proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@EnableAsync
public class ScheduledTaskLauncher {

    @Autowired
    private FeignProxy feignProxy;

    @Scheduled(cron = "0 18 * * *")
    public void runScheduledTask(){

    }
}
