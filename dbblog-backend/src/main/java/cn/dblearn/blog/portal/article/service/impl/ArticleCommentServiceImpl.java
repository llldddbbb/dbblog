package cn.dblearn.blog.portal.article.service.impl;

import cn.dblearn.blog.manage.article.mapper.ArticleCommentMapper;
import cn.dblearn.blog.manage.article.entity.ArticleComment;
import cn.dblearn.blog.portal.article.service.ArticleCommentService;
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
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Override
    public List<ArticleComment> listByArticleId(Integer articleId) {
        return baseMapper.listByArticleId(articleId);
    }
}
