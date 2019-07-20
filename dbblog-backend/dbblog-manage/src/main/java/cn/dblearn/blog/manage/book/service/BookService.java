package cn.dblearn.blog.manage.book.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.book.Book;
import cn.dblearn.blog.entity.book.dto.BookDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
public interface BookService extends IService<Book> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存图书
      * @param book
     */
    void saveBook(BookDTO book);

    /**
     * 获取图书对象
     * @param id
     * @return
     */
    BookDTO getBook(String id);

    /**
     * 更新图书
     * @param book
     */
    void updateBook(BookDTO book);

    /**
     * 批量删除
     * @param bookIds
     */
    void deleteBatch(Integer[] bookIds);

    /**
     *
     * @param categoryId
     * @return
     */
    boolean checkByCategory(Integer categoryId);
}
