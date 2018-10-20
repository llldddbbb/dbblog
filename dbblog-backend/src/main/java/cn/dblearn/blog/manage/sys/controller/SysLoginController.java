package cn.dblearn.blog.manage.sys.controller;

import cn.dblearn.blog.common.exception.enums.ErrorEnum;
import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.sys.pojo.entity.SysUser;
import cn.dblearn.blog.manage.sys.pojo.form.SysLoginForm;
import cn.dblearn.blog.manage.sys.service.SysCaptchaService;
import cn.dblearn.blog.manage.sys.service.SysUserService;
import cn.dblearn.blog.manage.sys.service.SysUserTokenService;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * SysLoginController
 *
 * @author bobbi
 * @date 2018/10/19 18:47
 * @email 571002217@qq.com
 * @description
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysCaptchaService sysCaptchaService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/admin/login")
    public Result login(@RequestBody SysLoginForm form) {
        boolean captcha=sysCaptchaService.validate(form.getUuid(),form.getCaptcha());
        if(!captcha){
            // 验证码不正确
            return Result.error(ErrorEnum.CAPTCHA_WRONG);
        }

        // 用户信息
        SysUser user=sysUserService.queryByUsername(form.getUsername());
        if(user ==null || !user.getPassword().equals(new Sha256Hash(form.getPassword(),user.getSalt()).toHex())){
            // 用户名或密码错误
            return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }

        //生成token，并保存到redis
        return sysUserTokenService.createToken(user.getUserId());
    }
}
