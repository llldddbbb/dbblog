package cn.dblearn.blog.manage.article.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.article.entity.ArticleCategory;
import cn.dblearn.blog.manage.article.entity.ArticleOrientation;
import cn.dblearn.blog.manage.article.entity.ArticleTag;
import cn.dblearn.blog.manage.article.mapper.ArticleCategoryMapper;
import cn.dblearn.blog.manage.article.mapper.ArticleOrientationMapper;
import cn.dblearn.blog.manage.article.mapper.ArticleTagMapper;
import cn.dblearn.blog.manage.article.service.ArticleClassifyAdminService;
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
public class ArticleClassifyAdminServiceImpl implements ArticleClassifyAdminService {

    @Autowired
    private ArticleOrientationMapper blogOrientationMapper;

    @Autowired
    private ArticleCategoryMapper blogCategoryMapper;

    @Autowired
    private ArticleTagMapper blogTagMapper;


    @Override
    public PageUtils queryPageOrientation(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<ArticleOrientation> page = blogOrientationMapper.selectPage(new Query<ArticleOrientation>(params).getPage(),
                new QueryWrapper<ArticleOrientation>().lambda().like(!StringUtils.isEmpty(name), ArticleOrientation::getName, name));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageCategory(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<ArticleCategory> page = blogCategoryMapper.selectPage(new Query<ArticleCategory>(params).getPage(),
                new QueryWrapper<ArticleCategory>().lambda().like(!StringUtils.isEmpty(name), ArticleCategory::getName, name));
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageTag(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<ArticleTag> page = blogTagMapper.selectPage(new Query<ArticleTag>(params).getPage(),
                new QueryWrapper<ArticleTag>().lambda().like(!StringUtils.isEmpty(name), ArticleTag::getTagName, name));
        return new PageUtils(page);
    }
}
