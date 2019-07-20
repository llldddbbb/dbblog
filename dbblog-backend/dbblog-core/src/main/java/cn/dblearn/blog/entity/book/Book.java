package cn.dblearn.blog.entity.book;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 图书表
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
@Data
@ApiModel(value="ReadBook对象", description="图书表")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    @ApiModelProperty(value = "出版社")
    private String publisher;

    @ApiModelProperty(value = "出版日期")
    private Date publishDate;

    @ApiModelProperty(value = "页数")
    private int pageNum;

    @ApiModelProperty(value = "评分")
    private Double grade;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "原书目录")
    private String catalogue;

    @ApiModelProperty(value = "阅读量")
    private Integer readNum;

    @ApiModelProperty(value = "点赞量")
    private Integer likeNum;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "是否发布")
    private Boolean publish;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "读书进度")
    private Integer progress;


    @ApiModelProperty(value = "是否正在阅读")
    private Boolean reading;


}
