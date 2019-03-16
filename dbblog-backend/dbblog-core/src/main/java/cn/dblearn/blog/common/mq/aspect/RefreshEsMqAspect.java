package cn.dblearn.blog.common.mq.aspect;

import cn.dblearn.blog.common.constants.RabbitMqConstants;
import cn.dblearn.blog.common.util.RabbitMqUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RefreshEsMqAspect
 *
 * @author bobbi
 * @date 2019/03/16 22:53
 * @email 571002217@qq.com
 * @description
 */
@Aspect
@Component
public class RefreshEsMqAspect {

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    @Pointcut("@annotation(cn.dblearn.blog.common.mq.annotation.RefreshEsMqSender)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();
        // 发送刷新信息
        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE,"send refresh Es");
        return result;
    }
}
