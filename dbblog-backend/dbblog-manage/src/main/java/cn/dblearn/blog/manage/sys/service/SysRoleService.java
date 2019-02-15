package cn.dblearn.blog.manage.sys.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.sys.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * SysRoleService
 *
 * @author bobbi
 * @date 2018/10/25 15:36
 * @email 571002217@qq.com
 * @description
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 批量删除
     * @param roleIds
     */
    void deleteBatch(Integer[] roleIds);

    /**
     * 查询roleId
     * @param createUserId
     * @return
     */
    List<Integer> queryRoleIdList(Integer createUserId);
}
