package cn.dblearn.blog.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorEnum
 *
 * @author bobbi
 * @date 2018/10/07 14:19
 * @email 571002217@qq.com
 * @description error类型枚举类
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    // 系统错误
    UNKNOWN(500,"系统内部错误，请联系管理员"),
    //用户权限错误
    NO_AUTH(401,"没有权限，请联系管理员"),
    INVALID_TOKEN(1001,"token不合法"),

    //登录模块错误
    LOGIN_FAIL(10001,"登录失败"),
    CAPTCHA_WRONG(10002,"验证码错误"),
    USERNAME_OR_PASSWORD_WRONG(10003,"用户名或密码错误");

    private int code;
    private String msg;

}
