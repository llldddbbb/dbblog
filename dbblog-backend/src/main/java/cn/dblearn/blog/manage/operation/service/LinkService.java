package cn.dblearn.blog.manage.operation.service;

import cn.dblearn.blog.manage.operation.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dblearn.blog.common.util.PageUtils;
import java.util.Map;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-02-14
 */
public interface LinkService extends IService<Link> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}
