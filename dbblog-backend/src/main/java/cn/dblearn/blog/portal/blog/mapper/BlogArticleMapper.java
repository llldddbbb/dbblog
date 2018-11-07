package cn.dblearn.blog.portal.blog.mapper;

import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.pojo.vo.BlogArticleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
     * @return
     */
    List<BlogArticleVo> listHomeArticle();
}
