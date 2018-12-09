package cn.dblearn.blog.portal.blog.controller;


import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.blog.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@RestController
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping("article/{articleId}")
    public Result getArticle(@PathVariable Integer articleId){
        BlogArticle article = blogArticleService.getArticle(articleId);
        return Result.ok().put("article",article);
    }

    @GetMapping("/articles/latest")
    public Result listLatest(){
        return Result.ok().put("articleList",blogArticleService.listLatest());
    }

    @GetMapping("/articles/favorite")
    public Result listFavorite(){
        return Result.ok().put("articleList",blogArticleService.listFavorite());
    }

    @GetMapping("/articles/commentMost")
    public Result listCommentMost(){
        return Result.ok().put("articleList",blogArticleService.listCommentMost());
    }

    @GetMapping("/articles/recommend")
    public Result listRecommend() {
        return Result.ok().put("articleList", blogArticleService.listRecommend());
    }

    @GetMapping("/articles/classify")
    public Result listByClassifyId(@RequestParam Map<String,Object> params){
        return Result.ok().put("articleList", blogArticleService.listByClassifyId(params));
    }
}
