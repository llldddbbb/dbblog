package cn.dblearn.blog.portal.blog.controller;


import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@RestController
@RequestMapping("/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    @GetMapping("/{articleId}")
    public Result getArticle(@PathVariable Integer articleId){
        BlogArticle article = blogArticleService.getArticle(articleId);
        return Result.ok().put("article",article);
    }
}
