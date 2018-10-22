package cn.dblearn.blog.manage.sys.service;

import cn.dblearn.blog.manage.sys.pojo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUser queryByUsername(String username);

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);
}
