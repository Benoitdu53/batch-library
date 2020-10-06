package com.web.library.batchlibrary.service;

import com.web.library.batchlibrary.model.LoginBean;
import com.web.library.batchlibrary.proxy.FeignProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTaskLauncher {
    private static Logger logger = LoggerFactory.getLogger(ScheduledTaskLauncher.class);

    @Autowired
    private FeignProxy feignProxy;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "*/1 * * * * ?")
    public void runScheduledTask(){
        String accessToken = feignProxy.validationAuthentication(new LoginBean()).getHeaders().getFirst("Authorization");
        mailService.sendMailReturnBook(accessToken);
        logger.info("Mail envoyé");
    }
}
