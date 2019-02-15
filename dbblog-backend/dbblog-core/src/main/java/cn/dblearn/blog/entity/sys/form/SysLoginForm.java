package cn.dblearn.blog.entity.sys.form;

import lombok.Data;

/**
 * SysLoginForm
 *
 * @author bobbi
 * @date 2018/10/20 14:51
 * @email 571002217@qq.com
 * @description 登录表单对象
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
