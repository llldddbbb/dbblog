package cn.dblearn.blog.manage.sys.mapper;

import cn.dblearn.blog.manage.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> queryAllPerms(Integer userId);
}
