package cn.dblearn.blog.manage.blog.controller;

import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.manage.blog.service.BlogClassifyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * BlogClassifyAdminController
 *
 * @author bobbi
 * @date 2018/12/05 22:46
 * @email 571002217@qq.com
 * @description
 */
@RestController
@RequestMapping("/admin/blog/classify")
public class BlogClassifyAdminController {

    @Autowired
    private BlogClassifyAdminService blogClassifyAdminService;

    @GetMapping("/orientation/list")
    public Result listOrientation(@RequestBody Map<String,Object> params) {
        PageUtils page = blogClassifyAdminService.queryPageOrientation(params);
        return Result.ok().put("orientationPage",page);
    }
    @GetMapping("/category/list")
    public Result listCategory(@RequestBody Map<String,Object> params) {
        PageUtils page = blogClassifyAdminService.queryPageCategory(params);
        return Result.ok().put("categoryPage",page);
    }
    @GetMapping("/tag/list")
    public Result listTag(@RequestBody Map<String,Object> params) {
        PageUtils page = blogClassifyAdminService.queryPageTag(params);
        return Result.ok().put("page",page);
    }

}
