package cn.dblearn.blog.mapper.timeline;

import cn.dblearn.blog.entity.timeline.Timeline;
import cn.dblearn.blog.entity.timeline.TimelinePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TimeLineMapper
 *
 * @author bobbi
 * @date 2019/02/24 20:53
 * @email 571002217@qq.com
 * @description
 */
@Mapper
public interface TimelineMapper {

    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);

    List<Timeline> listTimeline();
}
