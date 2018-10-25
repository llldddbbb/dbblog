package cn.dblearn.blog.manage.sys.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.sys.mapper.SysRoleMapper;
import cn.dblearn.blog.manage.sys.pojo.entity.SysRole;
import cn.dblearn.blog.manage.sys.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SysRoleServiceImpl
 *
 * @author bobbi
 * @date 2018/10/25 15:36
 * @email 571002217@qq.com
 * @description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Long createUserId = (Long)params.get("createUserId");
        IPage<SysRole> page=baseMapper.selectPage(new Query<SysRole>(params).getPage(),
                new QueryWrapper<SysRole>().lambda()
                .like(StringUtils.isNotBlank(roleName),SysRole::getRoleName,roleName)
                .eq(createUserId!=null,SysRole::getCreateUserId,createUserId)
        );
        return new PageUtils(page);
    }
}
