package cn.dblearn.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * BlogApplication
 *
 * @author: bobbi
 * @date: 2018/10/07 10:47
 * @description: 启动类
 */
@SpringBootApplication
@Configuration
public class BlogApplication {


    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
