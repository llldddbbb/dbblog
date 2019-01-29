package cn.dblearn.blog.manage.read.mapper;

import cn.dblearn.blog.manage.read.entity.ReadBook;
import cn.dblearn.blog.manage.read.entity.vo.ReadBookVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-01-27
 */
@Mapper
public interface ReadBookMapper extends BaseMapper<ReadBook> {

    /**
     * 获取bookVo
     * @param page
     * @param params
     * @return
     */
    List<ReadBookVo> listBookVo(Page<ReadBookVo> page, @Param("params") Map<String, Object> params);
}
