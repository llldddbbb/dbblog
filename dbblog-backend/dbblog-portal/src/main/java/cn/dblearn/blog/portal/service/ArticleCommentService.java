package cn.dblearn.blog.portal.service;

import cn.dblearn.blog.entity.article.ArticleComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章评论 服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-10
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    /**
     * 根据文章Id获取文章评论
     * @param articleId
     * @return
     */
    List<ArticleComment> listByArticleId(Integer articleId);
}
