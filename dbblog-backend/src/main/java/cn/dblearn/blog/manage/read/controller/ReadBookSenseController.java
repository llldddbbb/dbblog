package cn.dblearn.blog.manage.read.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.manage.read.entity.ReadBookSense;
import cn.dblearn.blog.manage.read.service.ReadBookSenseService;
import cn.dblearn.blog.manage.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
@RequestMapping("/admin/read/book/sense")
public class ReadBookSenseController extends AbstractController {

    @Autowired
    private ReadBookSenseService readBookSenseService;

    @GetMapping("/{bookId}")
    @RequiresPermissions("read:book:info")
    public Result getReadSense(@PathVariable Integer bookId) {
        ReadBookSense readSense = readBookSenseService.getReadSense(bookId);
        return Result.ok().put("readSense",readSense);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("read:book:save")
    public Result save(@RequestBody ReadBookSense readBookSense) {
        ValidatorUtils.validateEntity(readBookSense);
        readBookSenseService.save(readBookSense);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("read:book:update")
    public Result update(@RequestBody ReadBookSense readBookSense) {
        ValidatorUtils.validateEntity(readBookSense);
        readBookSense.setUpdateTime(LocalDateTime.now());
        readBookSenseService.updateById(readBookSense);
        return Result.ok();
    }
}
