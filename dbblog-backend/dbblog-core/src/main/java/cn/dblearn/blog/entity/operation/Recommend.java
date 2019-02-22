package cn.dblearn.blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 推荐
 * </p>
 *
 * @author bobbi
 * @since 2019-02-22
 */
@Data
@ApiModel(value="Recommend对象", description="推荐")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐的文章Id")
    private Integer linkId;

    @ApiModelProperty(value = "推荐类型")
    private Integer type;

    @ApiModelProperty(value = "顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "置顶")
    private Boolean top;


}
