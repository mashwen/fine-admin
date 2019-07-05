package com.ant.shop.admin.scheduled;

import com.ant.shop.admin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author:aijiaxiang
 * Date:2019/7/5
 * Description:
 */
@Component
public class ResourceScheduled {
    @Autowired
    private ResourceService resourceService;

    /**
     * 容器启动后30秒调用
     */
    @Scheduled(initialDelay = 30000,fixedRate = 60000*60*48)
    public void getAllResource(){
        resourceService.loadResourceDefine();
    }

}
