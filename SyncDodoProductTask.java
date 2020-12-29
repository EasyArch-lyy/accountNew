/*
 * ---------------------------------------------------------------------------
 * Copyright ? 2017 Hangzhou DtDream Technology Co.,Lt d. All rights reserved.
 * ---------------------------------------------------------------------------
 *         Product: TDEV
 *         Module Name: DTP
 *         Date Created: 2020-03-10
 *         Description:
 * ---------------------------------------------------------------------------
 * Modification History
 *         DATE            Name           Description
 * ---------------------------------------------------------------------------
 *         2020-03-10      卫一 1551
 * ---------------------------------------------------------------------------
 */

package com.dtdream.dtp.task;

import com.dtdream.dtp.common.Constants;
import com.dtdream.dtp.utils.FileHandle;
import com.dtdream.dtp.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by 卫一 on 2020-03-10 .
 * 更新 DodoProduct
 */
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SyncDodoProductTask {
    private static final Logger logger = LoggerFactory.getLogger(SyncDodoProductTask.class);

    /**  每天0点10分执行一次 */
    @Scheduled(cron = "0 10 0 * * ?")
    public void updateDodoProduct() {
        logger.info("开始同步Dodo产品");
        logger.info("同步dodo产品发送API:" + Constants.Local_Server_Url + "dtp/periodTask/syncDodoProducts" + "  " + new Date());
        Tools.GetResponse getResponse = Tools.GetResponse.generateHttpGetRequest(Constants.Local_Server_Url + "dtp/periodTask/syncDodoProducts");

        if (getResponse != null) {
            FileHandle.safeClose(getResponse);
            logger.info("dodo产品同步成功");
        } else {
            logger.info("dodo产品同步失败" + new Date());
        }
    }
}
