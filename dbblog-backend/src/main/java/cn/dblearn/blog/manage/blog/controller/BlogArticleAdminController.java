package cn.dblearn.blog.manage.blog.controller;

import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.blog.pojo.BlogArticle;
import cn.dblearn.blog.manage.blog.service.BlogArticleAdminService;
import cn.dblearn.blog.manage.blog.service.CloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public Result saveBlog(@RequestBody BlogArticle blogArticle){
        blogArticleAdminService.save(blogArticle);
        return Result.ok();
    }

    @RequestMapping("/cover/upload")
    public Result uploadCover(MultipartFile file) throws Exception{
        if (file.isEmpty()) {
            throw new MyException("上传文件不能为空");
        }
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url =cloudStorageService.uploadSuffix(file.getBytes(), suffix);
        return Result.ok().put("url", url);
    }

}
