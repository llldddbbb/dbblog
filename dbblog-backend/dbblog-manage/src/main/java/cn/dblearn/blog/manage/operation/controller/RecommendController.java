package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.base.AbstractController;
import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.entity.operation.Recommend;
import cn.dblearn.blog.entity.operation.vo.RecommendVo;
import cn.dblearn.blog.manage.operation.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2019-02-22
 */
@RestController
@Slf4j
@RequestMapping("/admin/operation/recommend")
public class RecommendController extends AbstractController {
    @Autowired
    private RecommendService recommendService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:recommend:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = recommendService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @GetMapping("/select")
    @RequiresPermissions("operation:recommend:list")
    public Result select () {
        List<RecommendVo> recommendList = recommendService.listSelect();
        return Result.ok().put("recommendList",recommendList);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("operation:recommend:info")
    public Result info(@PathVariable("id") String id){
       Recommend recommend = recommendService.getById(id);

        return Result.ok().put("recommend", recommend);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("operation:recommend:save")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result save(@RequestBody Recommend recommend){
        ValidatorUtils.validateEntity(recommend);
        recommendService.save(recommend);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result update(@RequestBody Recommend recommend){
        ValidatorUtils.validateEntity(recommend);
        recommendService.updateById(recommend);
        return Result.ok();
    }

    @PutMapping("/top/{id}")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result updateTop (@PathVariable Integer id) {
        recommendService.updateTop(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("operation:recommend:delete")
    @CacheEvict(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public Result delete(@RequestBody String[] ids){
        recommendService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
