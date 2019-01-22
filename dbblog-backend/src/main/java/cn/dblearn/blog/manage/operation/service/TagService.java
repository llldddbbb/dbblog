package cn.dblearn.blog.manage.operation.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.manage.operation.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-21
 */
public interface TagService extends IService<Tag> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据文章Id获取列表
     * @param articleId
     * @return
     */
    List<Tag> listByArticleId(Integer articleId);
}
