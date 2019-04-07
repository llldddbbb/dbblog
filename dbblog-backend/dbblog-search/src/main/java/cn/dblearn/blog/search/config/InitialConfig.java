package cn.dblearn.blog.search.config;

import cn.dblearn.blog.search.controller.ArticleEsController;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * InitialConfig
 *
 * @author bobbi
 * @date 2019/03/16 23:04
 * @email 571002217@qq.com
 * @description
 */
@Configuration
public class InitialConfig {

    @Resource
    private ArticleEsController  articleEsController;

    /**
     * 项目启动时重新导入索引
     */
    @PostConstruct
    public void initEsIndex(){
        try {
            articleEsController.refresh("initial Index");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
