package cn.dblearn.blog.manage.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 类别方向
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@Data
@ApiModel(value="BlogOrientation对象", description="类别方向")
public class BlogOrientation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "orientation_id", type = IdType.AUTO)
    private Integer orientationId;

    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 前端高亮状态
     */
    @TableField(exist = false)
    private Boolean active;


}
