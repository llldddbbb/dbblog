package cn.dblearn.blog.entity.operation;

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
@TableName("tag_link")
public class TagLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer linkId;

    private Integer tagId;

    @ApiModelProperty(value = "所属类型：0文章，1阅读")
    private Integer type;

    public TagLink() {
    }

    public TagLink(Integer linkId, Integer tagId,Integer type) {
        this.linkId =  linkId;
        this.tagId = tagId;
        this.type = type;
    }

}
