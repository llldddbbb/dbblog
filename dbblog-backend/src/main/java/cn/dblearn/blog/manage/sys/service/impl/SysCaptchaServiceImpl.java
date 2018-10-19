package cn.dblearn.blog.manage.sys.service.impl;

import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.common.exception.enums.ExceptionEnum;
import cn.dblearn.blog.manage.sys.service.SysCaptchaService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * SysCaptchaServiceImpl
 *
 * @author bobbi
 * @date 2018/10/19 18:56
 * @email 571002217@qq.com
 * @description
 */
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    private Producer producer;
    /**
     * 获取验证码
     *
     * @param uuid
     * @return
     */
    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isEmpty(uuid)){
            throw new MyException(ExceptionEnum.NO_UUID);
        }
        //生成文字验证码
        String code = producer.createText();

        return null;
    }

    /**
     * 验证验证码
     *
     * @param uuid
     * @param code
     * @return
     */
    @Override
    public boolean validate(String uuid, String code) {
        return false;
    }
}
