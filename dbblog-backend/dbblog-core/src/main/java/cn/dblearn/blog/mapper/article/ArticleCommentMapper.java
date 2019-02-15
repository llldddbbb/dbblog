package cn.dblearn.blog.mapper.article;

import cn.dblearn.blog.entity.article.ArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章评论 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-11-10
 */
@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    /**
     * 根据articleId查询评论
     * @param articleId
     * @return
     */
    List<ArticleComment> listByArticleId(Integer articleId);
}
