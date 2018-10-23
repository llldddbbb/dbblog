package cn.dblearn.blog.manage.sys.controller;


import cn.dblearn.blog.common.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController extends AbstractController{

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(){
        return Result.ok().put("user", getUser());
    }

}
