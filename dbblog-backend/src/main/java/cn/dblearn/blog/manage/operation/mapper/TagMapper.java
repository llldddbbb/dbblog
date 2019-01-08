package cn.dblearn.blog.manage.operation.mapper;

import cn.dblearn.blog.manage.operation.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据foreignId获取Tag列表
     * @param foreignId
     * @param type
     * @return
     */
    List<Tag> listByForeignId(@Param("foreignId") Integer foreignId, @Param("type")Integer type);

    /**
     * 根据foreignId删除多对多关联
     * @param foreignId
     * @param type
     */
    void deleteTagLink(@Param("foreignId") Integer foreignId, @Param("type")Integer type);
}
