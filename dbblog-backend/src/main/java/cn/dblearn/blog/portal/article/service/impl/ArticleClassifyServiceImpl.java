package cn.dblearn.blog.portal.article.service.impl;

import cn.dblearn.blog.manage.article.mapper.ArticleCategoryMapper;
import cn.dblearn.blog.manage.article.mapper.ArticleOrientationMapper;
import cn.dblearn.blog.manage.article.mapper.ArticleTagMapper;
import cn.dblearn.blog.manage.article.entity.ArticleCategory;
import cn.dblearn.blog.manage.article.entity.ArticleOrientation;
import cn.dblearn.blog.manage.article.entity.ArticleTag;
import cn.dblearn.blog.portal.article.service.ArticleClassifyService;
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
public class ArticleClassifyServiceImpl implements ArticleClassifyService {

    @Autowired
    private ArticleOrientationMapper blogOrientationMapper;

    @Autowired
    private ArticleCategoryMapper blogCategoryMapper;

    @Autowired
    private ArticleTagMapper blogTagMapper;

    /**
     * 获取分类方向列表
     *
     * @return
     */
    @Override
    public List<ArticleOrientation> listOrientation() {
        return blogOrientationMapper.selectList(null);
    }

    /**
     * 根据分类方向Id获取分类类别列表
     *
     * @param orientationId
     * @return
     */
    @Override
    public List<ArticleCategory> listCategoryByOrId(Integer orientationId) {
        return blogCategoryMapper.selectList(new QueryWrapper<ArticleCategory>().lambda()
        .eq(ArticleCategory::getOrientationId,orientationId));
    }

    /**
     * 根据类别Id获取分类标签列表
     *
     * @param categoryId
     * @return
     */
    @Override
    public Object listTagByCaId(Integer categoryId) {
        return blogTagMapper.selectList(new QueryWrapper<ArticleTag>().lambda()
        .eq(ArticleTag::getCategoryId,categoryId));
    }
}
