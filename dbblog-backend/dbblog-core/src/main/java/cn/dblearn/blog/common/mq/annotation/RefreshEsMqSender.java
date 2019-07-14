package cn.dblearn.blog.common.mq.annotation;

import java.lang.annotation.*;

/**
 * RefreshEsMqSender
 *
 * @author bobbi
 * @date 2019/03/16 22:52
 * @email 571002217@qq.com
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshEsMqSender {
    String sender();

    String msg() default "send refresh msg to ElasticSearch";

}
