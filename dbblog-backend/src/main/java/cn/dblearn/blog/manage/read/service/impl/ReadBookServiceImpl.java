package cn.dblearn.blog.manage.read.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.manage.operation.service.TagService;
import cn.dblearn.blog.manage.read.entity.ReadBook;
import cn.dblearn.blog.manage.read.entity.dto.ReadBookDto;
import cn.dblearn.blog.manage.read.mapper.ReadBookMapper;
import cn.dblearn.blog.manage.read.service.ReadBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.common.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReadBook> page=baseMapper.selectPage(new Query<ReadBook>(params).getPage(),
                new QueryWrapper<ReadBook>().lambda());
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

}
