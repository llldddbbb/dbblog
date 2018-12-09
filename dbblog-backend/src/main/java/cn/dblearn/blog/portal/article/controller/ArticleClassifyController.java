package cn.dblearn.blog.portal.article.controller;


import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.portal.article.service.ArticleClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章分类 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@RestController
@RequestMapping("article/classify")
public class ArticleClassifyController {

    @Autowired
    private ArticleClassifyService blogArticleClassifyService;

    @GetMapping("/orientations")
    public Result listOrientation(){
        return Result.ok().put("orientationList",blogArticleClassifyService.listOrientation());
    }

    @GetMapping("/categories")
    public Result listCategoryByOrId(@RequestParam Integer orientationId){
        return Result.ok().put("categoryList",blogArticleClassifyService.listCategoryByOrId(orientationId));
    }

    @GetMapping("/tags")
    public Result listTagByCaId(@RequestParam Integer categoryId){
        return Result.ok().put("tagList",blogArticleClassifyService.listTagByCaId(categoryId));
    }

}
