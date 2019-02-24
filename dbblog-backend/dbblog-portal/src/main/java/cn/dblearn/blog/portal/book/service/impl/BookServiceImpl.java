package cn.dblearn.blog.portal.book.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.entity.book.Book;
import cn.dblearn.blog.entity.book.vo.BookVo;
import cn.dblearn.blog.manage.book.service.BookSenseService;
import cn.dblearn.blog.manage.operation.service.TagService;
import cn.dblearn.blog.mapper.book.BookMapper;
import cn.dblearn.blog.portal.book.service.BookNoteService;
import cn.dblearn.blog.portal.book.service.BookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
@Service("bookPortalService")
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private TagService tagService;

    @Resource
    private BookSenseService bookSenseService;

    @Resource
    private BookNoteService bookNoteService;

    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookVo> page = new Query<BookVo>(params).getPage();
        List<BookVo> bookList = baseMapper.queryPageCondition(page, params);
        if (!CollectionUtils.isEmpty(bookList)) {
            bookList.forEach(bookVo -> {
                // 设置标签列表
                bookVo.setTagList(tagService.listByLinkId(bookVo.getId(), ModuleEnum.BOOK.getValue()));
            });
        }

        page.setRecords(bookList);
        return new PageUtils(page);
    }

    /**
     * 获取Book对象
     *
     * @param id
     * @return
     */
    @Override
    public BookVo getBookVo(Integer id) {
        BookVo book = Optional.ofNullable(this.baseMapper.selectById(id)).flatMap(book1 -> {
            BookVo bookVo = new BookVo();
            BeanUtils.copyProperties(book1, bookVo);
            return Optional.ofNullable(bookVo);
        }).map(bookVo -> {
            bookVo.setTagList(tagService.listByLinkId(bookVo.getId(), ModuleEnum.BOOK.getValue()));
            bookVo.setBookNoteList(bookNoteService.listSimpleBookNote(bookVo.getId()));
            bookVo.setBookSense(bookSenseService.getBookSense(bookVo.getId()));
            return bookVo;
        }).orElse(null);
        return book;
    }
}
