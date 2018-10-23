package cn.dblearn.blog.manage.sys.controller;


import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.sys.pojo.entity.SysMenu;
import cn.dblearn.blog.manage.sys.service.ShiroService;
import cn.dblearn.blog.manage.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController extends AbstractController{

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/nav")
    public Result nav(){
        List<SysMenu> menuList=sysMenuService.listUserMenu(getUserId());
        Set<String> permissions=shiroService.getUserPermissions(getUserId());
        return Result.ok().put("menuList",menuList).put("permissions",permissions);
    }

}
