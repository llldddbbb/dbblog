package cn.dblearn.blog.entity.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户Token
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Data
@ApiModel(value="SysUserToken对象", description="系统用户Token")
public class SysUserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String token;



}
