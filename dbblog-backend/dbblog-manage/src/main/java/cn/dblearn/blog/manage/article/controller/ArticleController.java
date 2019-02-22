package cn.dblearn.blog.manage.article.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.entity.article.dto.ArticleDto;
import cn.dblearn.blog.manage.article.service.ArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * ArticleAdminController
 *
 * @author bobbi
 * @date 2018/11/20 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/list")
    @RequiresPermissions("article:list")
    public Result listArticle(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPage(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/info/{articleId}")
    @RequiresPermissions("article:list")
    public Result info(@PathVariable Integer articleId) {
        ArticleDto article = articleService.getArticle(articleId);
        return Result.ok().put("article",article);
    }

    @PostMapping("/save")
    @RequiresPermissions("article:save")
    public Result saveArticle(@RequestBody ArticleDto article){
        ValidatorUtils.validateEntity(article);
        articleService.saveArticle(article);
        return Result.ok();
    }

    @PutMapping("/update")
    @RequiresPermissions("article:update")
    public Result updateArticle(@RequestBody ArticleDto article){
        ValidatorUtils.validateEntity(article);
        article.setUpdateTime(LocalDateTime.now());
        articleService.updateArticle(article);
        return Result.ok();
    }
    
    @PutMapping("/update/status")
    @RequiresPermissions("article:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result updateStatus(@RequestBody Article article) {
        articleService.updateById(article);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @RequiresPermissions("article:delete")
    public Result deleteBatch(@RequestBody Integer[] articleIds){
        articleService.deleteBatch(articleIds);
        return Result.ok();
    }

}
