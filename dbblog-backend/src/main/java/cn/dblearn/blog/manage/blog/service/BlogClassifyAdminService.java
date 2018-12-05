package cn.dblearn.blog.manage.blog.service;

import cn.dblearn.blog.common.util.PageUtils;

import java.util.Map;

/**
 * <p>
 * 文章分类 服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
public interface BlogClassifyAdminService {

    PageUtils queryPageOrientation(Map<String, Object> params);

    PageUtils queryPageCategory(Map<String, Object> params);

    PageUtils queryPageTag(Map<String, Object> params);
}
