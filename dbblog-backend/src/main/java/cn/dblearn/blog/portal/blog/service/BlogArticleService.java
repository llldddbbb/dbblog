package cn.dblearn.blog.portal.blog.service;

import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
