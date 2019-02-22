package cn.dblearn.blog.entity.operation.vo;

import cn.dblearn.blog.entity.operation.Recommend;
import cn.dblearn.blog.entity.operation.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * RecommendVo
 *
 * @author bobbi
 * @date 2019/02/22 10:49
 * @email 571002217@qq.com
 * @description
 */
@Data
public class RecommendVo extends Recommend {

    private String description;

    private Long readNum;

    private Long commentNum;

    private Long likeNum;

    private String urlType;

    private String cover;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private List<Tag> tagList;

}
