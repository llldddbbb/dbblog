package cn.dblearn.blog.portal.blog.service;

import cn.dblearn.blog.manage.blog.pojo.BlogArticle;
import cn.dblearn.blog.portal.blog.pojo.vo.BlogArticleVo;
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
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    BlogArticle getArticle(Integer articleId);

    /**
     * 获取最新文章
     * @return
     */
    List<BlogArticleVo> listLatest();

    /**
     * 获取点赞最多的文章
     * @return
     */
    List<BlogArticleVo> listFavorite();

    /**
     * 获取评论最多的文章
     * @return
     */
    List<BlogArticleVo> listCommentMost();

    /**
     * 获取推荐文章
     * @return
     */
    List<BlogArticleVo> listRecommend();

    /**
     * 根据分类Id筛选文章列表
     * @param params
     * @return
     */
    List<BlogArticleVo> listByClassifyId(Map<String, Object> params);
}
