package cn.dblearn.blog.portal.blog.mapper;

import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.pojo.vo.BlogArticleVo;
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
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    /**
     * 查询首页文章列表
     * @param map
     * @return
     */
    List<BlogArticleVo> listHomeArticle(Map<String,Object> map);

    /**
     * 根据分类Id获取文章列表
     * @param params
     * @return
     */
    List<BlogArticleVo> listByClassifyId(Map<String, Object> params);
}
