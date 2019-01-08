package cn.dblearn.blog.manage.operation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章标签多对多维护表
 * </p>
 *
 * @author bobbi
 * @since 2019-01-07
 */
@Data
@ApiModel(value="TagLink对象", description="标签多对多维护表")
@TableName("operation_tag_link")
public class TagLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer foreignId;

    private Integer tagId;

    @ApiModelProperty(value = "所属类型：0文章，1阅读")
    private Integer type;

    public TagLink() {
    }

    public TagLink(Integer foreignId, Integer tagId) {
        this.foreignId =  foreignId;
        this.tagId = tagId;
    }

}
