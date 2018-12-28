package cn.dblearn.blog.manage.operation.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.manage.operation.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-12-17
 */
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询分类
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
