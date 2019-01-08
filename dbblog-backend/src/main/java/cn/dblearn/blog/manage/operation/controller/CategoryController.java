package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.enums.CategoryRankEnum;
import cn.dblearn.blog.common.exception.MyException;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.manage.operation.entity.Category;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import cn.dblearn.blog.manage.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<Category> categoryList = categoryService.queryAll(params);
        return Result.ok().put("categoryList",categoryList);
    }

    /**
     * 树状列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("operation:category:list")
    public Result select(){
        List<Category> categoryList = categoryService.list(null);

        //添加顶级分类
        Category root = new Category();
        root.setId(-1);
        root.setName("根目录");
        root.setParentId(-1);
        categoryList.add(root);

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
        // 数据校验
        ValidatorUtils.validateEntity(category);
        verifyCategory(category);
        categoryService.save(category);

        return Result.ok();
    }

    /**
     * 数据校验
     * @param category
     */
    private void verifyCategory(Category category) {
        //上级分类级别
        int parentRank = CategoryRankEnum.ROOT.getValue();
        if (category.getParentId() != CategoryRankEnum.FIRST.getValue()
                && category.getParentId() != CategoryRankEnum.ROOT.getValue()) {
            Category parentCategory = categoryService.getById(category.getParentId());
            parentRank = parentCategory.getRank();
        }

        // 一级
        if (category.getRank() == CategoryRankEnum.FIRST.getValue()) {
            if (category.getParentId() != CategoryRankEnum.ROOT.getValue()){
                throw new MyException("上级目录只能为根目录");
            }
        }

        //二级
        if (category.getRank() == CategoryRankEnum.SECOND.getValue()) {
            if (parentRank != CategoryRankEnum.FIRST.getValue()) {
                throw new MyException("上级目录只能为一级类型");
            }
        }

        //三级
        if (category.getRank() == CategoryRankEnum.THIRD.getValue()) {
            if (parentRank != CategoryRankEnum.SECOND.getValue()) {
                throw new MyException("上级目录只能为二级类型");
            }
        }
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
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("operation:category:delete")
    public Result delete(@PathVariable Integer id){

        //判断是否有子菜单或按钮
        List<Category> categoryList = categoryService.queryListParentId(id);
        if(categoryList.size() > 0){
            return Result.error("请先删除子级别");
        }

        categoryService.removeById(id);

        return Result.ok();
    }
}
