package cn.dblearn.blog.manage.article.mapper;

import cn.dblearn.blog.manage.article.entity.ArticleTag;
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
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 根据articleId获取Tag列表
     * @param articleId
     * @return
     */
    List<ArticleTag> listByArticleId(Integer articleId);
}
