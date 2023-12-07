package com.wil.job;

import com.wil.entity.Paper;
import com.wil.entity.PaperExample;
import com.wil.mapper.PaperMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wil on 2018/5/24.
 */
@Component
public class PaperStateChangeJob {

    private Logger logger = LoggerFactory.getLogger(PaperStateChangeJob.class);

    @Autowired
    private PaperMapper paperMapper;

    @Scheduled(cron = "0 31 10,17,22 * * ?")
    public void run() {
        List<Paper> paperList = paperMapper.selectByExample(new PaperExample());
        for(Paper paper : paperList) {
            if(paper.isEnd()) {
                paper.setPaperState("已结束");
                paperMapper.updateByPrimaryKeySelective(paper);
                logger.info("试卷:{} 状态被修改:{}", paper.getPaperName(), paper.getPaperState());
            }
        }

    }

}
