package cn.dblearn.blog.manage.sys.util;

import cn.dblearn.blog.common.util.JsonUtils;
import cn.dblearn.blog.manage.sys.pojo.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RedisTest
 *
 * @author bobbi
 * @date 2018/10/19 21:16
 * @email 571002217@qq.com
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedis() throws Exception {
        SysUser user=new SysUser();
        user.setUserId(1);
        user.setUsername("Bobbi");
        redisTemplate.opsForValue().set("user", JsonUtils.toJson(user));
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
