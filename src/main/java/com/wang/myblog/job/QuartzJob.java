package com.wang.myblog.job;


import com.wang.myblog.service.BlogService;
import com.wang.myblog.service.LikeService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJob.class);

    @Autowired
    private LikeService likeService;
    @Autowired
    private BlogService blogService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            blogService.writeUserViews2DB();
            System.out.println("userviews写入成功");
            blogService.setView();
            System.out.println("博客访问量写入成功");
            likeService.saveLiked2DB();
            System.out.println("喜欢写入成功");
            likeService.saveLikedCount2DB();
            System.out.println("喜欢计数成功");
            blogService.writeHistoricalViews();
            System.out.println("历史访问量写入成功");
        } catch (Exception e) {
            LOGGER.error("任务执行出错："+e.getMessage());
        }
    }
}
