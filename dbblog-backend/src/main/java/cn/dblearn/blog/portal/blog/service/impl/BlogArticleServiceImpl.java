package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.portal.blog.mapper.BlogArticleMapper;
import cn.dblearn.blog.portal.blog.mapper.BlogTagMapper;
import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.service.BlogArticleCommentService;
import cn.dblearn.blog.portal.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogArticleCommentService blogArticleCommentService;

    /**
     * 获取文章详情
     *
     * @param articleId
     * @return
     */
    @Override
    public BlogArticle getArticle(Integer articleId) {
        BlogArticle article = baseMapper.selectById(articleId);
        //所属标签
        article.setTagList(blogTagMapper.listByArticleId(articleId));
        //所属评论
        article.setCommentList(blogArticleCommentService.listByArticleId(articleId));
        return article;
    }
}
