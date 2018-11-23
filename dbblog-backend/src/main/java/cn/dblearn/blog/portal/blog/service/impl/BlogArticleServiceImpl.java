package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.common.util.MapUtils;
import cn.dblearn.blog.manage.blog.mapper.BlogArticleMapper;
import cn.dblearn.blog.manage.blog.mapper.BlogTagMapper;
import cn.dblearn.blog.manage.blog.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.pojo.vo.BlogArticleVo;
import cn.dblearn.blog.portal.blog.service.BlogArticleCommentService;
import cn.dblearn.blog.portal.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 获取最新文章
     *
     * @return
     */
    @Override
    public List<BlogArticleVo> listLatest() {
        return baseMapper.listHomeArticle(new MapUtils().put("latest",true));
    }

    /**
     * 获取点赞最多的文章
     *
     * @return
     */
    @Override
    public List<BlogArticleVo> listFavorite() {
        return baseMapper.listHomeArticle(new MapUtils().put("favorite",true));
    }

    /**
     * 获取评论最多的文章
     *
     * @return
     */
    @Override
    public List<BlogArticleVo> listCommentMost() {
        return baseMapper.listHomeArticle(new MapUtils().put("commentMost",true));
    }

    /**
     * 获取推荐文章
     *
     * @return
     */
    @Override
    public List<BlogArticleVo> listRecommend() {
        return baseMapper.listHomeArticle(new MapUtils().put("isRecommend",true));
    }

    /**
     * 根据分类Id获取文章列表
     *
     * @param params
     * @return
     */
    @Override
    public List<BlogArticleVo> listByClassifyId(Map<String,Object> params) {
        return baseMapper.listByClassifyId(params);
    }


}
