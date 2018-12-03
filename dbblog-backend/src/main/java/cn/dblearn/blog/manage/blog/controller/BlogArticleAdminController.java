package cn.dblearn.blog.manage.blog.controller;

import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.common.validator.group.AddGroup;
import cn.dblearn.blog.manage.blog.entity.BlogArticle;
import cn.dblearn.blog.manage.blog.service.BlogArticleAdminService;
import cn.dblearn.blog.manage.oss.service.CloudStorageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private CloudStorageService cloudStorageService;

    @Autowired
    private BlogArticleAdminService blogArticleAdminService;

    @PostMapping("/save")
    @RequiresPermissions("blog:article:add")
    public Result saveBlog(@RequestBody BlogArticle blogArticle){
        ValidatorUtils.validateEntity(blogArticle, AddGroup.class);
        blogArticleAdminService.saveArticle(blogArticle);
        return Result.ok();
    }

    @PostMapping("/cover/upload")
    public Result uploadCover(MultipartFile file) throws Exception{
        if (file.isEmpty()) {
            throw new MyException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url =cloudStorageService.uploadSuffix(file.getBytes(), suffix);
        return Result.ok().put("url", url).put("name",file.getOriginalFilename());
    }

    @GetMapping("/list")
    public Result listBlog(@RequestParam Map<String, Object> params) {
        PageUtils page = blogArticleAdminService.queryPage(params);
        return Result.ok().put("page",page);
    }

    @DeleteMapping("/delete")
    public Result deleteBatch(@RequestBody Integer[] articleIds){
        blogArticleAdminService.deleteBatch(articleIds);
        return Result.ok();
    }

}
