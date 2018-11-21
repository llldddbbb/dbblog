package cn.dblearn.blog.manage.blog.mapper;

import cn.dblearn.blog.manage.blog.pojo.BlogArticleComment;
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
public interface BlogArticleCommentMapper extends BaseMapper<BlogArticleComment> {

    /**
     * 根据articleId查询评论
     * @param articleId
     * @return
     */
    List<BlogArticleComment> listByArticleId(Integer articleId);
}
