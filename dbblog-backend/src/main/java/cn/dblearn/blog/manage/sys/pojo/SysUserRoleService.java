package cn.dblearn.blog.manage.sys.pojo;

import cn.dblearn.blog.manage.sys.pojo.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserRoleService extends IService<SysUserRole> {

    void deleteBatch(Integer[] roleIds);
}
