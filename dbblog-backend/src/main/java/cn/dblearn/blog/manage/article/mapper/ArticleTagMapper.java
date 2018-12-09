package cn.dblearn.blog.manage.blog.mapper;

import cn.dblearn.blog.manage.blog.entity.BlogTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 标签 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Mapper
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    /**
     * 根据articleId获取Tag列表
     * @param articleId
     * @return
     */
    List<BlogTag> listByArticleId(Integer articleId);
}
