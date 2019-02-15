package cn.dblearn.blog.portal.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.portal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PortalController
 *
 * @author bobbi
 * @date 2018/11/07 17:32
 * @email 571002217@qq.com
 * @description
 */
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"/","/home","/index"})
    public Result home(){
        return homeService.listHomeData();
    }
}
