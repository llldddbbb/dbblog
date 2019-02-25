package cn.dblearn.blog.portal.common.aspect;

import cn.dblearn.blog.common.util.HttpContextUtils;
import cn.dblearn.blog.common.util.IPUtils;
import cn.dblearn.blog.common.util.JsonUtils;
import cn.dblearn.blog.mapper.article.ArticleMapper;
import cn.dblearn.blog.mapper.book.BookMapper;
import cn.dblearn.blog.mapper.book.BookNoteMapper;
import cn.dblearn.blog.mapper.log.LogLikeMapper;
import cn.dblearn.blog.mapper.log.LogViewMapper;
import cn.dblearn.blog.portal.common.annotation.LogLike;
import cn.dblearn.blog.portal.common.annotation.LogView;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * ViewLogAspect
 *
 * @author bobbi
 * @date 2019/02/15 14:56
 * @email 571002217@qq.com
 * @description
 */
@Aspect
@Component
@Slf4j
public class LogLikeAspect {

    @Autowired
    private LogLikeMapper logLikeMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookNoteMapper bookNoteMapper;

    @Pointcut("@annotation(cn.dblearn.blog.portal.common.annotation.LogLike)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveLogLike(point, time);

        return result;
    }

    private void saveLogLike(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        cn.dblearn.blog.entity.log.LogLike logLikeEntity = new cn.dblearn.blog.entity.log.LogLike();
        LogLike viewLog = method.getAnnotation(LogLike.class);
        //注解上的类型
        String type = viewLog.type();
        logLikeEntity.setType(type);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String id = JsonUtils.toJson(args[0]);
        // 根据注解类型增加数量
        switch (type) {
            case "article":
                articleMapper.updateLikeNum(Integer.parseInt(id));
                break;
            case "book":
                bookMapper.updateLikeNum(Integer.parseInt(id));
                break;
            case "bookNote":
                bookNoteMapper.updateLikeNum(Integer.parseInt(id));
                break;
            default:
                break;
        }
        logLikeEntity.setParams(id);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        logLikeEntity.setIp(IPUtils.getIpAddr(request));
        logLikeEntity.setTime(time);
        logLikeEntity.setCreateDate(LocalDateTime.now());
        logLikeMapper.insert(logLikeEntity);

    }
}
