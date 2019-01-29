package cn.dblearn.blog.manage.read.service;

import cn.dblearn.blog.manage.read.entity.ReadBook;
import cn.dblearn.blog.manage.read.entity.dto.ReadBookDto;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dblearn.blog.common.util.PageUtils;

import java.util.Map;

/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
public interface ReadBookService extends IService<ReadBook> {

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
    void saveBook(ReadBookDto book);

    /**
     * 获取图书对象
     * @param id
     * @return
     */
    ReadBookDto getBook(String id);

    /**
     * 更新图书
     * @param book
     */
    void updateBook(ReadBookDto book);

    /**
     * 批量删除
     * @param bookIds
     */
    void deleteBatch(Integer[] bookIds);
}
