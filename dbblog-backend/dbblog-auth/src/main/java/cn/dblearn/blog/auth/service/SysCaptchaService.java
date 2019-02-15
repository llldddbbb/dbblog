package cn.dblearn.blog.auth.service;


import java.awt.image.BufferedImage;

/**
 * SysCaptchaService
 *
 * @author bobbi
 * @date 2018/10/19 18:52
 * @email 571002217@qq.com
 * @description 验证码类
 */
public interface SysCaptchaService {

    /**
     * 获取验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证验证码
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}
