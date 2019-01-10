package cn.dblearn.blog.manage.article.mapper;

import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.article.entity.vo.ArticleVo;
import cn.dblearn.blog.portal.article.pojo.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询首页文章列表
     * @param map
     * @return
     */
    List<ArticleVO> listHomeArticle(Map<String,Object> map);

    /**
     * 根据分类Id获取文章列表
     * @param params
     * @return
     */
    List<ArticleVO> listByClassifyId(Map<String, Object> params);

    /**
     * 查询列表
     * @param params
     * @return
     */
    List<ArticleVo> listArticleVo(Map<String, Object> params);
}
