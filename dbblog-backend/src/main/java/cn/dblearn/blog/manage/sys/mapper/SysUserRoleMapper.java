package cn.dblearn.blog.manage.sys.mapper;

import cn.dblearn.blog.manage.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
