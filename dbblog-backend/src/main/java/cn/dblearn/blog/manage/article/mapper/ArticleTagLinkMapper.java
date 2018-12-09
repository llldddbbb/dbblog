package cn.dblearn.blog.manage.article.mapper;

import cn.dblearn.blog.manage.article.entity.ArticleTagLink;
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
public interface ArticleTagLinkMapper extends BaseMapper<ArticleTagLink> {

}
