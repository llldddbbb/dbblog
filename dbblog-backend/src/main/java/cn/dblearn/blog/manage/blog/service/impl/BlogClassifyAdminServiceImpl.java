package cn.dblearn.blog.manage.blog.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.blog.entity.BlogCategory;
import cn.dblearn.blog.manage.blog.entity.BlogOrientation;
import cn.dblearn.blog.manage.blog.entity.BlogTag;
import cn.dblearn.blog.manage.blog.mapper.BlogCategoryMapper;
import cn.dblearn.blog.manage.blog.mapper.BlogOrientationMapper;
import cn.dblearn.blog.manage.blog.mapper.BlogTagMapper;
import cn.dblearn.blog.manage.blog.service.BlogClassifyAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@Service
public class BlogClassifyAdminServiceImpl implements BlogClassifyAdminService {

    @Autowired
    private BlogOrientationMapper blogOrientationMapper;

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;


    @Override
    public PageUtils queryPageOrientation(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<BlogOrientation> page = blogOrientationMapper.selectPage(new Query<BlogOrientation>(params).getPage(),
                new QueryWrapper<BlogOrientation>().lambda().like(!StringUtils.isEmpty(name), BlogOrientation::getName, name));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageCategory(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<BlogCategory> page = blogCategoryMapper.selectPage(new Query<BlogCategory>(params).getPage(),
                new QueryWrapper<BlogCategory>().lambda().like(!StringUtils.isEmpty(name), BlogCategory::getName, name));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageTag(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<BlogTag> page = blogTagMapper.selectPage(new Query<BlogTag>(params).getPage(),
                new QueryWrapper<BlogTag>().lambda().like(!StringUtils.isEmpty(name), BlogTag::getTagName, name));
        return new PageUtils(page);
    }
}
