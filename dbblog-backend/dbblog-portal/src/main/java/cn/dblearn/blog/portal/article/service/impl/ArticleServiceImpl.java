package cn.dblearn.blog.portal.article.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.entity.article.vo.ArticleVo;
import cn.dblearn.blog.mapper.article.ArticleMapper;
import cn.dblearn.blog.portal.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Service("ArticlePortalService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    /**
     * 获取文章详情
     *
     * @param articleId
     * @return
     */
    @Override
    public Article getArticle(Integer articleId) {
        return baseMapper.selectById(articleId);
    }

    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<ArticleVo> page = new Query<ArticleVo>(params).getPage();
        List<ArticleVo> articleList = baseMapper.queryPageCondition(page, params);
        page.setRecords(articleList);
        return new PageUtils(page);
    }
}
