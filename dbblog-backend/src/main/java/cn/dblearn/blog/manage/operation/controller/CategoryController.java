package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.operation.entity.Category;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import cn.dblearn.blog.manage.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-12-17
 */
@RestController
@Slf4j
@RequestMapping("/admin/operation/category")
public class CategoryController extends AbstractController {


    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operation:category:list")
    public Result list(@RequestParam Map<String, Object> params){
        List<Category> categoryList = categoryService.list(null);
        return Result.ok().put("categoryList",categoryList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operation:category:info")
    public Result info(@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);

        return Result.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operation:category:save")
    public Result save(@RequestBody Category category){
        categoryService.save(category);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operation:category:update")
    public Result update(@RequestBody Category category){
        categoryService.updateById(category);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operation:category:delete")
    public Result delete(@RequestBody Integer[] ids){
        categoryService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
