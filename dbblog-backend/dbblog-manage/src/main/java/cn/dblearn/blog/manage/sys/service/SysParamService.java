package cn.dblearn.blog.manage.sys.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.sys.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-12-28
 */
public interface SysParamService extends IService<SysParam> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}
