package cn.dblearn.blog.manage.blog.mapper;

import cn.dblearn.blog.manage.blog.entity.BlogArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签多对多维护表 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-11-23
 */
@Mapper
public interface BlogArticleTagMapper extends BaseMapper<BlogArticleTag> {

}
