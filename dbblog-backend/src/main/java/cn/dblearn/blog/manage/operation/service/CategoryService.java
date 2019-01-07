package cn.dblearn.blog.manage.operation.service;

import cn.dblearn.blog.manage.operation.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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
     * 查询所有菜单
     * @param params
     * @return
     */
    List<Category> queryAll(Map<String, Object> params);

    /**
     * 根据父级别查询子级别
     * @param id
     * @return
     */
    List<Category> queryListParentId(Integer id);
}
