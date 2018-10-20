package cn.dblearn.blog.manage.sys.service.impl;

import cn.dblearn.blog.manage.sys.pojo.entity.SysUser;
import cn.dblearn.blog.manage.sys.mapper.SysUserMapper;
import cn.dblearn.blog.manage.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser queryByUsername(String username) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>()
                .lambda()
                .eq(SysUser :: getUsername,username));
    }
}
