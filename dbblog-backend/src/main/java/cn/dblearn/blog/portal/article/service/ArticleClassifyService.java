package cn.dblearn.blog.portal.article.service;

import cn.dblearn.blog.manage.article.entity.ArticleCategory;
import cn.dblearn.blog.manage.article.entity.ArticleOrientation;

import java.util.List;

/**
 * <p>
 * 文章分类 服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
public interface ArticleClassifyService {

    /**
     * 获取分类方向列表
     * @return
     */
    List<ArticleOrientation> listOrientation();

    /**
     * 根据分类方向Id获取分类类别列表
     * @param orientationId
     * @return
     */
    List<ArticleCategory> listCategoryByOrId(Integer orientationId);

    /**
     * 根据类别Id获取分类标签列表
     * @param categoryId
     * @return
     */
    Object listTagByCaId(Integer categoryId);
}
