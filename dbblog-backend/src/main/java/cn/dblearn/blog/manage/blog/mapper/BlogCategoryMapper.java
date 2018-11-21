package cn.dblearn.blog.manage.blog.mapper;

import cn.dblearn.blog.manage.blog.pojo.BlogCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章分类 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2018-11-11
 */
@Mapper
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

}
