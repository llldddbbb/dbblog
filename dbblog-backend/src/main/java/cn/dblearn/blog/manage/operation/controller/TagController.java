package cn.dblearn.blog.manage.operation.controller;

import cn.dblearn.blog.common.Result;
import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.operation.service.TagService;
import cn.dblearn.blog.manage.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * TagController
 *
 * @author bobbi
 * @date 2019/01/08 16:24
 * @email 571002217@qq.com
 * @description
 */
@RestController
@Slf4j
@RequestMapping("/admin/operation/tag")
public class TagController extends AbstractController {

    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public Result list() {
        List<Tag> tagList = tagService.list(null);
        return Result.ok().put("tagList",tagList);
    }
}
