package cn.dblearn.blog.manage.sys.service.impl;

import cn.dblearn.blog.entity.sys.SysUserRole;
import cn.dblearn.blog.mapper.sys.SysUserRoleMapper;
import cn.dblearn.blog.manage.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SysUserRoleServiceImpl
 *
 * @author Zoey
 * @date 2018/10/26 00:01
 * @email 571002217@qq.com
 * @description
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void deleteBatchByRoleId(Integer[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> {
            baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
            .eq(roleId!=null, SysUserRole::getRoleId,roleId));
        });
    }

    @Override
    public void deleteBatchByUserId(Integer[] userIds) {
        Arrays.stream(userIds).forEach(userId -> {
            baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
                    .eq(userId!=null, SysUserRole::getUserId,userId));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
        //先删除用户与角色关系
        baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
                .eq(userId!=null, SysUserRole::getUserId,userId));

        if(roleIdList.size() == 0){
            return ;
        }

        //保存用户与角色关系
        List<SysUserRole> list = new ArrayList<>(roleIdList.size());
        for(Integer roleId : roleIdList){
            SysUserRole SysUserRole = new SysUserRole();
            SysUserRole.setUserId(userId);
            SysUserRole.setRoleId(roleId);

            list.add(SysUserRole);
        }
        this.saveBatch(list);
    }

    /**
     * 根据userId查询roleId
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        return baseMapper.queryRoleIdList(userId);
    }
}
