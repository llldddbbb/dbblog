package cn.dblearn.blog.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 阅读日志
 * </p>
 *
 * @author bobbi
 * @since 2019-02-15
 */
@Data
@ApiModel(value="ViewLog对象", description="阅读日志")
public class LogView implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "浏览类型")
    private String type;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


}
