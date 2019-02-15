package cn.dblearn.blog.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章评论
 * </p>
 *
 * @author bobbi
 * @since 2018-11-10
 */
@Data
@ApiModel(value="BlogArticleComment对象", description="文章评论")
public class ArticleComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "关联父Id")
    private Integer parentId;

    @ApiModelProperty(value = "关联文章Id")
    private Integer articleId;

    @ApiModelProperty(value = "点赞数量")
    private Integer likeNum;

    @ApiModelProperty(value = "不喜欢数量")
    private Integer dislikeNum;

    @ApiModelProperty(value = "评论层级")
    private Integer commentLevel;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @TableField(exist = false)
    private String parentNickName;


}
