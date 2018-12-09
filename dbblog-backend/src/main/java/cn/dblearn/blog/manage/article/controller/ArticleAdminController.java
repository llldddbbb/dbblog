package cn.dblearn.blog.manage.blog.controller;

import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.common.validator.group.AddGroup;
import cn.dblearn.blog.manage.blog.entity.BlogArticle;
import cn.dblearn.blog.manage.blog.service.BlogArticleAdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * BlogArticleAdminController
 *
 * @author bobbi
 * @date 2018/11/20 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController
@RequestMapping("/admin/blog/article")
public class BlogArticleAdminController {

    @Autowired
    private BlogArticleAdminService blogArticleAdminService;

    @GetMapping("/list")
    @RequiresPermissions("blog:article:list")
    public Result listBlog(@RequestParam Map<String, Object> params) {
        PageUtils page = blogArticleAdminService.queryPage(params);
        return Result.ok().put("page",page);
    }

    @PostMapping("/save")
    @RequiresPermissions("blog:article:save")
    public Result saveBlog(@RequestBody BlogArticle blogArticle){
        ValidatorUtils.validateEntity(blogArticle, AddGroup.class);
        blogArticleAdminService.saveArticle(blogArticle);
        return Result.ok();
    }

    @PutMapping("/update")
    @RequiresPermissions("blog:article:update")
    public Result updateBlog(@RequestBody BlogArticle blogArticle){
        ValidatorUtils.validateEntity(blogArticle, AddGroup.class);
        blogArticleAdminService.updateArticle(blogArticle);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @RequiresPermissions("blog:article:delete")
    public Result deleteBatch(@RequestBody Integer[] articleIds){
        blogArticleAdminService.deleteBatch(articleIds);
        return Result.ok();
    }

}
