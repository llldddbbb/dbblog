package cn.dblearn.blog.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ExceptionEnum
 *
 * @author bobbi
 * @date 2018/10/07 13:57
 * @email 571002217@qq.com
 * @description 异常类型枚举
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum{
    // 系统异常模块
    UNKNOWN(500,"系统内部错误，请联系管理员"),
    PATH_NOT_FOUND(404,"路径不存在，请检查路径"),
    NO_AUTH(403,"没有权限，请联系管理员"),
    DUPLICATE_KEY(501,"数据库中已存在该记录"),
    TOKEN_GENERATOR_ERROR(502,"token生成失败"),
    NO_UUID(503,"uuid为空");

    private int code;
    private String msg;

}
