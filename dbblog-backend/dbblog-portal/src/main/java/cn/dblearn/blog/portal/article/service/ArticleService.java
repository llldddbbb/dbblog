package cn.dblearn.blog.portal.article.service;


import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.article.Article;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);
}
