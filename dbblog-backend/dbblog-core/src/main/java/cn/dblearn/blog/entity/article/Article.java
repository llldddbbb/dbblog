package cn.dblearn.blog.entity.article;

import cn.dblearn.blog.common.validator.group.AddGroup;
import cn.dblearn.blog.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    @ApiModelProperty(value = "文章展示类别,0:普通，1：大图片，2：无图片")
    private Integer coverType;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否推荐文章")
    private Boolean recommend;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "发布状态")
    private Boolean publish;

    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

}
