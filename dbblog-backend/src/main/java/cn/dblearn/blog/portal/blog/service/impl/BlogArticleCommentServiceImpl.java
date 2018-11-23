package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.manage.blog.mapper.BlogArticleCommentMapper;
import cn.dblearn.blog.manage.blog.entity.BlogArticleComment;
import cn.dblearn.blog.portal.blog.service.BlogArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章评论 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-10
 */
@Service
public class BlogArticleCommentServiceImpl extends ServiceImpl<BlogArticleCommentMapper, BlogArticleComment> implements BlogArticleCommentService {

    @Override
    public List<BlogArticleComment> listByArticleId(Integer articleId) {
        return baseMapper.listByArticleId(articleId);
    }
}
