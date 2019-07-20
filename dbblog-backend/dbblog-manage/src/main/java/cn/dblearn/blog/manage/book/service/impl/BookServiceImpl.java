package cn.dblearn.blog.manage.book.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.entity.book.Book;
import cn.dblearn.blog.entity.book.dto.BookDTO;
import cn.dblearn.blog.entity.book.vo.BookVO;
import cn.dblearn.blog.mapper.book.BookMapper;
import cn.dblearn.blog.manage.book.service.BookService;
import cn.dblearn.blog.entity.operation.Category;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import cn.dblearn.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookVO> page=new Query<BookVO>(params).getPage();
        List<BookVO> bookList = this.baseMapper.listBookVo(page,params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType,ModuleEnum.BOOK.getValue()));
        // 封装BookVo
        bookList.forEach(bookVo -> {
            // 设置类别
            bookVo.setCategoryListStr(categoryService.renderCategoryArr(bookVo.getCategoryId(),categoryList));
            // 设置标签列表
            bookVo.setTagList(tagService.listByLinkId(bookVo.getId(),ModuleEnum.BOOK.getValue()));
        });
        page.setRecords(bookList);
        return new PageUtils(page);
    }

    /**
     * 保存图书
     *
     * @param book
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBook(BookDTO book) {
       this.baseMapper.insert(book);
       tagService.saveTagAndNew(book.getTagList(),book.getId(), ModuleEnum.BOOK.getValue());
    }

    /**
     * 获取图书对象
     *
     * @param id
     * @return
     */
    @Override
    public BookDTO getBook(String id) {
        Book readBook = this.baseMapper.selectById(id);
        BookDTO readBookDto = new BookDTO();
        BeanUtils.copyProperties(readBook,readBookDto);
        readBookDto.setTagList(tagService.listByLinkId(readBook.getId(),ModuleEnum.BOOK.getValue()));
        return readBookDto;
    }

    /**
     * 更新图书
     *
     * @param book
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(BookDTO book) {
        // 删除多对多所属标签
        tagService.deleteTagLink(book.getId(),ModuleEnum.BOOK.getValue());
        // 更新所属标签
        tagService.saveTagAndNew(book.getTagList(),book.getId(), ModuleEnum.BOOK.getValue());
        // 更新图书
        baseMapper.updateById(book);
    }

    /**
     * 批量删除
     *
     * @param bookIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] bookIds) {
        //先删除标签多对多关联
        Arrays.stream(bookIds).forEach(bookId -> {
            tagService.deleteTagLink(bookId,ModuleEnum.BOOK.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(bookIds));
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }

}
