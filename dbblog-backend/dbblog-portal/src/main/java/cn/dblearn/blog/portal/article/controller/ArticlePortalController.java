package cn.dblearn.blog.portal.article.controller;


import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.portal.annotation.ViewLog;
import cn.dblearn.blog.portal.article.service.ArticleService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
public class ArticlePortalController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    @ViewLog(type = "article")
    public Result getArticle(@PathVariable Integer articleId){
        Article article = articleService.getById(articleId);
        return Result.ok().put("article",article);
    }

    @GetMapping("/articles/latest")
    public Result listLatest(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/articles/favorite")
    public Result listFavorite(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/articles/commentMost")
    public Result listCommentMost(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/articles/recommend")
    public Result listRecommend(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

}
