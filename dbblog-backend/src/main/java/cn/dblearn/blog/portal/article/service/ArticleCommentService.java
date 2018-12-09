package cn.dblearn.blog.portal.blog.service;

import cn.dblearn.blog.manage.blog.entity.BlogArticleComment;
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
public interface BlogCommentService extends IService<BlogArticleComment> {

    /**
     * 根据文章Id获取文章评论
     * @param articleId
     * @return
     */
    List<BlogArticleComment> listByArticleId(Integer articleId);
}
