package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.manage.blog.mapper.BlogCategoryMapper;
import cn.dblearn.blog.manage.blog.mapper.BlogOrientationMapper;
import cn.dblearn.blog.manage.blog.mapper.BlogTagMapper;
import cn.dblearn.blog.manage.blog.pojo.BlogCategory;
import cn.dblearn.blog.manage.blog.pojo.BlogOrientation;
import cn.dblearn.blog.manage.blog.pojo.BlogTag;
import cn.dblearn.blog.portal.blog.service.BlogArticleClassifyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@Service
public class BlogArticleClassifyServiceImpl implements BlogArticleClassifyService {

    @Autowired
    private BlogOrientationMapper blogOrientationMapper;

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    /**
     * 获取分类方向列表
     *
     * @return
     */
    @Override
    public List<BlogOrientation> listOrientation() {
        return blogOrientationMapper.selectList(null);
    }

    /**
     * 根据分类方向Id获取分类类别列表
     *
     * @param orientationId
     * @return
     */
    @Override
    public List<BlogCategory> listCategoryByOrId(Integer orientationId) {
        return blogCategoryMapper.selectList(new QueryWrapper<BlogCategory>().lambda()
        .eq(BlogCategory::getOrientationId,orientationId));
    }

    /**
     * 根据类别Id获取分类标签列表
     *
     * @param categoryId
     * @return
     */
    @Override
    public Object listTagByCaId(Integer categoryId) {
        return blogTagMapper.selectList(new QueryWrapper<BlogTag>().lambda()
        .eq(BlogTag::getCategoryId,categoryId));
    }
}
