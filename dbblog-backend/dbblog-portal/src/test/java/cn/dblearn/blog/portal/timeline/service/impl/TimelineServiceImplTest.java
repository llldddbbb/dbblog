package cn.dblearn.blog.portal.timeline.service.impl;

import cn.dblearn.blog.BlogApplication;
import cn.dblearn.blog.entity.timeline.Timeline;
import cn.dblearn.blog.portal.timeline.service.TimelineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class TimelineServiceImplTest {

    @Resource
    private TimelineService timelineService;

    @Test
    public void testListTimeline(){
        List<Timeline> timelineList = timelineService.listTimeLine();
        System.out.println(timelineList);
    }
}