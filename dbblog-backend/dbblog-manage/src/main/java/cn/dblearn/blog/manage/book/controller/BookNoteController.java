package cn.dblearn.blog.manage.book.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.entity.book.BookNote;
import cn.dblearn.blog.entity.book.dto.BookNoteDto;
import cn.dblearn.blog.manage.book.service.BookNoteService;
import cn.dblearn.blog.manage.operation.service.RecommendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


/**
 * BookNoteAdminController
 *
 * @author bobbi
 * @date 2018/11/20 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController
@RequestMapping("/admin/book/note")
public class BookNoteController {

    @Resource
    private BookNoteService bookNoteService;

    @Resource
    private RecommendService recommendService;

    @GetMapping("/list")
    @RequiresPermissions("book:note:list")
    public Result listBookNote(@RequestParam Map<String, Object> params) {
        PageUtils page = bookNoteService.queryPage(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/info/{bookNoteId}")
    @RequiresPermissions("book:note:list")
    public Result info(@PathVariable Integer bookNoteId) {
        BookNoteDto bookNote = bookNoteService.getBookNote(bookNoteId);
        return Result.ok().put("bookNote",bookNote);
    }

    @PostMapping("/save")
    @RequiresPermissions("book:note:save")
    public Result saveBookNote(@RequestBody BookNoteDto bookNote){
        ValidatorUtils.validateEntity(bookNote);
        bookNoteService.saveBookNote(bookNote);
        return Result.ok();
    }

    @PutMapping("/update")
    @RequiresPermissions("book:note:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result updateBookNote(@RequestBody BookNoteDto bookNote){
        ValidatorUtils.validateEntity(bookNote);
        bookNote.setUpdateTime(new Date());
        bookNoteService.updateBookNote(bookNote);
        return Result.ok();
    }
    
    @PutMapping("/update/status")
    @RequiresPermissions("book:note:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result updateStatus(@RequestBody BookNote bookNote) {
        bookNoteService.updateById(bookNote);
        return Result.ok();
    }


    @DeleteMapping("/delete")
    @RequiresPermissions("book:note:delete")
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result deleteBatch(@RequestBody Integer[] bookNoteIds){
        recommendService.deleteBatchByLinkId(bookNoteIds, ModuleEnum.BOOK_NOTE.getValue());
        bookNoteService.deleteBatch(bookNoteIds);
        return Result.ok();
    }

}
