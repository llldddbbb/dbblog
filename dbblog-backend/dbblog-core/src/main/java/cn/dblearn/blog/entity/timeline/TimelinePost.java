package cn.dblearn.blog.entity.timeline;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TimeLineData
 *
 * @author bobbi
 * @date 2019/02/24 20:39
 * @email 571002217@qq.com
 * @description
 */
@Data
public class TimelinePost {

    private Integer id;

    private String title;

    private String description;

    private String postType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

}
