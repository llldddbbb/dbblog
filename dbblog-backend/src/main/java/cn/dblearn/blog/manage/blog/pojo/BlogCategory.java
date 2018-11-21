package cn.dblearn.blog.manage.blog.pojo.blog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章分类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@Data
@ApiModel(value="BlogCategory对象", description="文章分类")
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "关联类别方向外键")
    private Integer orientationId;

    /**
     * 前端高亮状态
     */
    @TableField(exist = false)
    private Boolean active;

}
