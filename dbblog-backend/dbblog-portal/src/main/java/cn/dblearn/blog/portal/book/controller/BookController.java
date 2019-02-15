package cn.dblearn.blog.portal.book.controller;


import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.book.Book;
import cn.dblearn.blog.portal.annotation.ViewLog;
import cn.dblearn.blog.portal.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@RestController("bookPortalController")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{bookId}")
    @ViewLog(type = "book")
    public Result getBook(@PathVariable Integer bookId){
        Book book = bookService.getById(bookId);
        return Result.ok().put("book",book);
    }

    @GetMapping("/books/latest")
    public Result listLatest(@RequestParam Map<String, Object> params){
        params.put("latest",true);
        PageUtils page = bookService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/books/favorite")
    public Result listFavorite(@RequestParam Map<String, Object> params){
        params.put("favorite",true);
        PageUtils page = bookService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/books/commentMost")
    public Result listCommentMost(@RequestParam Map<String, Object> params){
        params.put("commentMost",true);
        PageUtils page = bookService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/books/recommend")
    public Result listRecommend(@RequestParam Map<String, Object> params) {
        params.put("recommend",true);
        PageUtils page = bookService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

}
