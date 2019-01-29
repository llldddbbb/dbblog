package cn.dblearn.blog.manage.read.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.operation.entity.Category;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import cn.dblearn.blog.manage.operation.service.TagService;
import cn.dblearn.blog.manage.read.entity.ReadBook;
import cn.dblearn.blog.manage.read.entity.dto.ReadBookDto;
import cn.dblearn.blog.manage.read.entity.vo.ReadBookVo;
import cn.dblearn.blog.manage.read.mapper.ReadBookMapper;
import cn.dblearn.blog.manage.read.service.ReadBookService;
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
public class ReadBookServiceImpl extends ServiceImpl<ReadBookMapper, ReadBook> implements ReadBookService {

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
        Page<ReadBookVo> page=new Query<ReadBookVo>(params).getPage();
        List<ReadBookVo> bookList = this.baseMapper.listBookVo(page,params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType,ModuleEnum.READING.getValue()));
        // 封装BookVo
        bookList.forEach(bookVo -> {
            // 设置类别
            bookVo.setCategoryListStr(categoryService.renderCategoryArr(bookVo.getCategoryId(),categoryList));
            // 设置标签列表
            bookVo.setTagList(tagService.listByLinkId(bookVo.getId(),ModuleEnum.READING.getValue()));
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
    public void saveBook(ReadBookDto book) {
       this.baseMapper.insert(book);
       tagService.saveTagAndNew(book.getTagList(),book.getId(), ModuleEnum.READING.getValue());
    }

    /**
     * 获取图书对象
     *
     * @param id
     * @return
     */
    @Override
    public ReadBookDto getBook(String id) {
        ReadBook readBook = this.baseMapper.selectById(id);
        ReadBookDto readBookDto = new ReadBookDto();
        BeanUtils.copyProperties(readBook,readBookDto);
        readBookDto.setTagList(tagService.listByLinkId(readBook.getId(),ModuleEnum.READING.getValue()));
        return readBookDto;
    }

    /**
     * 更新图书
     *
     * @param book
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(ReadBookDto book) {
        // 删除多对多所属标签
        tagService.deleteTagLink(book.getId(),ModuleEnum.READING.getValue());
        // 更新所属标签
        tagService.saveTagAndNew(book.getTagList(),book.getId(), ModuleEnum.READING.getValue());
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
            tagService.deleteTagLink(bookId,ModuleEnum.READING.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(bookIds));
    }

}
