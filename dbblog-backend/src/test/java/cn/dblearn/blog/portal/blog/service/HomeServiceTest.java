package cn.dblearn.blog.portal.blog.service;

import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * HomeService
 *
 * @author bobbi
 * @date 2018/11/07 21:18
 * @email 571002217@qq.com
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class HomeServiceTest {

    @Autowired
    private HomeService homeService;

    @Test
    public void homeServiceTest(){
        Result result = homeService.listHomeData();
        log.info(JsonUtils.toJson(result));
    }
}
