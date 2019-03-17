package cn.dblearn.blog.search.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.constants.RabbitMqConstants;
import cn.dblearn.blog.common.util.RabbitMqUtils;
import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.portal.article.service.ArticleService;
import cn.dblearn.blog.search.mapper.ArticleRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ArticleEsController
 *
 * @author bobbi
 * @date 2019/03/13 15:04
 * @email 571002217@qq.com
 * @description
 */
@RestController
public class ArticleEsController {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private ArticleService articleService;

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    /**
     * 搜索标题，描述，内容
     * @param keywords
     * @return
     */
    @GetMapping("articles/search")
    public Result search(@RequestParam("keywords") String keywords){
        articleRepository.refresh();
        // 对所有索引进行搜索
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(keywords);

        Iterable<Article> listIt =  articleRepository.search(queryBuilder);

        //Iterable转list
        List<Article> articleList= Lists.newArrayList(listIt);

        return Result.ok().put("articleList",articleList);
    }

    @RabbitListener(queues=RabbitMqConstants.REFRESH_ES_INDEX_QUEUE)
    public void refresh(String msg) {
        articleRepository.deleteAll();
        List<Article> list = articleService.list(null);
        articleRepository.saveAll(list);
        System.out.println(msg);
    }

}
