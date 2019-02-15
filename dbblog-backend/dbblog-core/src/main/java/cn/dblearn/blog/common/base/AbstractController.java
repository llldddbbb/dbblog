package cn.dblearn.blog.common.base;

import cn.dblearn.blog.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * AbstractController
 *
 * @author bobbi
 * @date 2018/10/22 12:35
 * @email 571002217@qq.com
 * @description
 */
public class AbstractController {

    protected SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId(){
        return getUser().getUserId();
    }
}
