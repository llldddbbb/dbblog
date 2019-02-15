package cn.dblearn.blog.mapper.operation;

import cn.dblearn.blog.entity.operation.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 友链 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-02-14
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
