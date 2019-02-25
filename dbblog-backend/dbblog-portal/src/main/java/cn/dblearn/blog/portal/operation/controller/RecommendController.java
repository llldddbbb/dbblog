package cn.dblearn.blog.portal.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.entity.operation.vo.RecommendVo;
import cn.dblearn.blog.portal.operation.service.RecommendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Recommend
 *
 * @author bobbi
 * @date 2019/02/22 13:40
 * @email 571002217@qq.com
 * @description
 */
@RestController("recommendPortalController")
@RequestMapping("/operation")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @RequestMapping("/recommends")
    public Result listRecommend() {
        List<RecommendVo> recommendList = recommendService.listRecommendVo();
        return Result.ok().put("recommendList",recommendList);
    }

    @RequestMapping("/hotReads")
    public Result listHotRead () {
        List<RecommendVo> hotReadList = recommendService.listHotRead();
        return Result.ok().put("hotReadList",hotReadList);
    }
}
