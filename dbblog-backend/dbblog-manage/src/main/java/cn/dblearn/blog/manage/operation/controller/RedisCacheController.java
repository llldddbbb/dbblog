package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.constants.RedisCacheNames;
import cn.dblearn.blog.common.util.RedisUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * BookNoteAdminController
 *
 * @author bobbi
 * @date 2019/11/12 20:25
 * @email 571002217@qq.com
 * @description
 */
@RestController
@RequestMapping("/admin/operation/redis")
public class RedisCacheController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


}
