package cn.dblearn.blog.manage.operation.service;

import cn.dblearn.blog.manage.operation.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * TagService
 *
 * @author bobbi
 * @date 2019/01/08 16:20
 * @email 571002217@qq.com
 * @description
 */
public interface TagService extends IService<Tag> {

    /**
     * 根据articleId获取Tag列表
     * @param articleId
     * @return
     */
    List<Tag> listByArticleId(Integer articleId);
}
