package cn.dblearn.blog.manage.blog.pojo;

import cn.dblearn.blog.common.validator.group.AddGroup;
import cn.dblearn.blog.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Data
@ApiModel(value="BlogArticle对象", description="文章")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message="博文标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String title;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message="博文内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String content;

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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否推荐文章")
    private Boolean isRecommend;

    @TableField(exist = false)
    private List<BlogTag> tagList;

    @TableField(exist = false)
    private List<BlogArticleComment> commentList;

}
