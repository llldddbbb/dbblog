package cn.dblearn.blog.manage.sys.service.impl;

import cn.dblearn.blog.manage.sys.mapper.SysUserRoleMapper;
import cn.dblearn.blog.manage.sys.pojo.SysUserRoleService;
import cn.dblearn.blog.manage.sys.pojo.entity.SysUserRole;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
    public void deleteBatch(Integer[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> {
            baseMapper.delete(new UpdateWrapper<SysUserRole>().lambda()
            .eq(roleId!=null,SysUserRole::getRoleId,roleId));
        });
    }
}
