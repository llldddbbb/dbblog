package cn.dblearn.blog.mapper.common;

import cn.dblearn.blog.entity.comment.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章评论 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-01-23
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
