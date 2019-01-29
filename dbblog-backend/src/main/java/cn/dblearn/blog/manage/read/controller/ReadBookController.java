package cn.dblearn.blog.manage.read.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.manage.read.entity.ReadBook;
import cn.dblearn.blog.manage.read.entity.dto.ReadBookDto;
import cn.dblearn.blog.manage.read.service.ReadBookService;
import cn.dblearn.blog.manage.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
@RestController
@Slf4j
@RequestMapping("/admin/read/book")
public class ReadBookController extends AbstractController {
    @Autowired
    private ReadBookService bookService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("read:book:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = bookService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("read:book:info")
    public Result info(@PathVariable("id") String id){
        ReadBookDto book = bookService.getBook(id);
        return Result.ok().put("book", book);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("read:book:save")
    public Result save(@RequestBody ReadBookDto book){
        ValidatorUtils.validateEntity(book);
        bookService.saveBook(book);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("read:book:update")
    public Result update(@RequestBody ReadBookDto book){
        ValidatorUtils.validateEntity(book);
        bookService.updateBook(book);
        return Result.ok();
    }

    /**
     * 更新状态
     * @param readBook
     * @return
     */
    @PutMapping("/update/status")
    @RequiresPermissions("read:book:update")
    public Result updateStatus(@RequestBody ReadBook readBook) {
        bookService.updateById(readBook);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("read:book:delete")
    public Result delete(@RequestBody Integer[] ids){
        bookService.deleteBatch(ids);

        return Result.ok();
    }
}
