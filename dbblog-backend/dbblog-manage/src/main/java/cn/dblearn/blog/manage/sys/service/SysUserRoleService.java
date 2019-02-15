package cn.dblearn.blog.manage.sys.service;

import cn.dblearn.blog.entity.sys.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 批量删除roleId
     * @param roleIds
     */
    void deleteBatchByRoleId(Integer[] roleIds);

    /**
     * 批量删除userId
     * @param userIds
     */
    void deleteBatchByUserId(Integer[] userIds);

    /**
     * 更新或保存用户角色
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Integer userId, List<Integer> roleIdList);

    /**
     * 根据userId查询roleId
     * @param userId
     * @return
     */
    List<Integer> queryRoleIdList(Integer userId);
}
