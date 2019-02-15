package cn.dblearn.blog.portal.service.impl;

import cn.dblearn.blog.common.util.MapUtils;
import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.article.mapper.ArticleMapper;
import cn.dblearn.blog.portal.article.pojo.vo.ArticleVO;
import cn.dblearn.blog.portal.article.service.ArticleCommentService;
import cn.dblearn.blog.portal.article.service.ArticleService;
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
@Service("ArticlePortalService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

//    @Autowired
//    private TagService tagService;

    @Autowired
    private ArticleCommentService blogArticleCommentService;

    /**
     * 获取文章详情
     *
     * @param articleId
     * @return
     */
    @Override
    public Article getArticle(Integer articleId) {

        Article article = baseMapper.selectById(articleId);
        //所属标签
//        article.setTagList(blogTagMapper.listByArticleId(id));
//        //所属评论
//        article.setCommentList(blogArticleCommentService.listByArticleId(id));
        return article;
    }

    /**
     * 获取最新文章
     *
     * @return
     */
    @Override
    public List<ArticleVO> listLatest() {
        return baseMapper.listHomeArticle(new MapUtils().put("latest",true));
    }

    /**
     * 获取点赞最多的文章
     *
     * @return
     */
    @Override
    public List<ArticleVO> listFavorite() {
        return baseMapper.listHomeArticle(new MapUtils().put("favorite",true));
    }

    /**
     * 获取评论最多的文章
     *
     * @return
     */
    @Override
    public List<ArticleVO> listCommentMost() {
        return baseMapper.listHomeArticle(new MapUtils().put("commentMost",true));
    }

    /**
     * 获取推荐文章
     *
     * @return
     */
    @Override
    public List<ArticleVO> listRecommend() {
        return baseMapper.listHomeArticle(new MapUtils().put("isRecommend",true));
    }

    /**
     * 根据分类Id获取文章列表
     *
     * @param params
     * @return
     */
    @Override
    public List<ArticleVO> listByClassifyId(Map<String,Object> params) {
        return baseMapper.listByClassifyId(params);
    }


}
