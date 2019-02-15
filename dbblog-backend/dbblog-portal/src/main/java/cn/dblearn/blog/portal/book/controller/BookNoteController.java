package cn.dblearn.blog.portal.book.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.book.BookNote;
import cn.dblearn.blog.portal.annotation.ViewLog;
import cn.dblearn.blog.portal.book.service.BookNoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * BookNoteNoteAdminController
 *
 * @author bobbi
 * @date 2018/11/20 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController("bookNotePortalController")
public class BookNoteController {

    @Resource
    private BookNoteService bookNoteService;
   

    @GetMapping("/bookNote/{bookNoteId}")
    @ViewLog(type = "bookNote")
    public Result getBookNote(@PathVariable Integer bookNoteId){
        BookNote bookNote = bookNoteService.getById(bookNoteId);
        return Result.ok().put("bookNote",bookNote);
    }

    @GetMapping("/bookNotes/latest")
    public Result listLatest(@RequestParam Map<String, Object> params){
        params.put("latest",true);
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/bookNotes/favorite")
    public Result listFavorite(@RequestParam Map<String, Object> params){
        params.put("favorite",true);
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/bookNotes/commentMost")
    public Result listCommentMost(@RequestParam Map<String, Object> params){
        params.put("commentMost",true);
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/bookNotes/recommend")
    public Result listRecommend(@RequestParam Map<String, Object> params) {
        params.put("recommend",true);
        PageUtils page = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

}
