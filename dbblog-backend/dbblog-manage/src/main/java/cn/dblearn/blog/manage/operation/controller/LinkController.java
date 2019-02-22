package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.common.base.AbstractController;
import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.validator.ValidatorUtils;
import cn.dblearn.blog.entity.operation.Link;
import cn.dblearn.blog.manage.operation.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2019-02-14
 */
@RestController
@Slf4j
@RequestMapping("/admin/operation/link")
public class LinkController extends AbstractController {
    @Autowired
    private LinkService linkService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:link:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = linkService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("operation:link:info")
    public Result info(@PathVariable("id") String id){
       Link link = linkService.getById(id);

        return Result.ok().put("link", link);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("operation:link:save")
    @CacheEvict(value = RedisKeyConstants.PORTAL_LINK_LIST)
    public Result save(@RequestBody Link link){
        ValidatorUtils.validateEntity(link);
        linkService.save(link);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("operation:link:update")
    @CacheEvict(value = RedisKeyConstants.PORTAL_LINK_LIST)
    public Result update(@RequestBody Link link){
        ValidatorUtils.validateEntity(link);
        linkService.updateById(link);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("operation:link:delete")
    @CacheEvict(value = RedisKeyConstants.PORTAL_LINK_LIST)
    public Result delete(@RequestBody String[] ids){
        linkService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}
