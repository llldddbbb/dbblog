package cn.dblearn.blog.portal.service;


import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.portal.ArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    Article getArticle(Integer articleId);

    /**
     * 获取最新文章
     * @return
     */
    List<ArticleVO> listLatest();

    /**
     * 获取点赞最多的文章
     * @return
     */
    List<ArticleVO> listFavorite();

    /**
     * 获取评论最多的文章
     * @return
     */
    List<ArticleVO> listCommentMost();

    /**
     * 获取推荐文章
     * @return
     */
    List<ArticleVO> listRecommend();

    /**
     * 根据分类Id筛选文章列表
     * @param params
     * @return
     */
    List<ArticleVO> listByClassifyId(Map<String, Object> params);
}
