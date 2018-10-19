package cn.dblearn.blog.manage.sys.service;

import cn.dblearn.blog.manage.sys.entity.SysUser;
import cn.dblearn.blog.manage.sys.entity.SysUserToken;

import java.util.Set;

/**
 * ShiroService
 *
 * @author bobbi
 * @date 2018/10/08 19:58
 * @email 571002217@qq.com
 * @description service接口类
 */
public interface ShiroService {

    /**
     * 获取用户的所有权限
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Integer userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    SysUser queryUser(Integer userId);
}
