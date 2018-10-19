package cn.dblearn.blog.manage.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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


    @GetMapping("capptcha.jpg")
    public void captcha(HttpServletResponse response,String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        // BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        /*ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);*/
    }
}
