package cn.dblearn.blog.portal.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.entity.operation.vo.TagVo;
import cn.dblearn.blog.portal.operation.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TagController
 *
 * @author bobbi
 * @date 2019/02/22 16:34
 * @email 571002217@qq.com
 * @description
 */
@RestController("tagPortalController")
@RequestMapping("/operation")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public Result listTag() {
        List<TagVo> tagList = tagService.listTagVo();
        return Result.ok().put("tagList",tagList);
    }

}
