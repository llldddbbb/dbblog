package cn.dblearn.blog.manage.comment.service;

import cn.dblearn.blog.manage.comment.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.dblearn.blog.common.util.PageUtils;
import java.util.Map;

/**
 * <p>
 * 文章评论 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-01-23
 */
public interface CommentService extends IService<Comment> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}
