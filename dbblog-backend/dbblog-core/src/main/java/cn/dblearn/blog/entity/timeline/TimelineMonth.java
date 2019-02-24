package cn.dblearn.blog.entity.timeline;

import lombok.Data;

import java.util.List;

/**
 * TimeLineMonh
 *
 * @author bobbi
 * @date 2019/02/24 20:33
 * @email 571002217@qq.com
 * @description
 */
@Data
public class TimelineMonth {

    private Integer month;

    private Integer count;

    private List<TimelinePost> posts;

}
