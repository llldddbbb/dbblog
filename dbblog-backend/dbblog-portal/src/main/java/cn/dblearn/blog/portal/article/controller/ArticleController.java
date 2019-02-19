package cn.dblearn.blog.portal.article.controller;


import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.portal.common.annotation.ViewLog;
import cn.dblearn.blog.portal.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@RestController("articlePortalController")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    @ViewLog(type = "article")
    public Result getArticle(@PathVariable Integer articleId){
        Article article = articleService.getById(articleId);
        return Result.ok().put("article",article);
    }

    @GetMapping("/articles")
    public Result listLatest(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

}
