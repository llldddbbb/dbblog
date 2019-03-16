package cn.dblearn.blog.manage.book.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.base.AbstractController;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.entity.book.BookSense;
import cn.dblearn.blog.manage.book.service.BookSenseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 读后感 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2019-02-13
 */
@RestController
@Slf4j
@RequestMapping("/admin/book/sense")
public class BookSenseController extends AbstractController {

    @Autowired
    private BookSenseService bookSenseService;

    @GetMapping("/{bookId}")
    @RequiresPermissions("book:info")
    public Result getReadSense(@PathVariable Integer bookId) {
        BookSense bookSense = bookSenseService.getBookSense(bookId);
        return Result.ok().put("bookSense",bookSense);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("book:save")
    public Result save(@RequestBody BookSense bookSense) {
        ValidatorUtils.validateEntity(bookSense);
        bookSenseService.save(bookSense);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("book:update")
    public Result update(@RequestBody BookSense bookSense) {
        ValidatorUtils.validateEntity(bookSense);
        bookSense.setUpdateTime(new Date());
        bookSenseService.updateById(bookSense);
        return Result.ok();
    }
}
