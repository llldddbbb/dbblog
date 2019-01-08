package cn.dblearn.blog.portal.article.pojo.vo;

import cn.dblearn.blog.manage.operation.entity.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * BlogArticleVo
 *
 * @author bobbi
 * @date 2018/11/07 20:39
 * @email 571002217@qq.com
 * @description
 */
@Data
public class ArticleVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer articleId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "阅读量")
    private Long readNum;

    @ApiModelProperty(value = "评论量")
    private Long commentNum;

    @ApiModelProperty(value = "点赞量")
    private Long likeNum;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "文章展示类别,1:普通，2：大图片，3：无图片")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 所关联的Tag列表
     */
    private List<Tag> tagList;
}
