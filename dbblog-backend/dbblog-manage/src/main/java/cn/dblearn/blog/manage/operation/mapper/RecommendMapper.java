package cn.dblearn.blog.manage.operation.mapper;

import cn.dblearn.blog.entity.operation.Recommend;
import cn.dblearn.blog.entity.operation.vo.RecommendVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 推荐 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-02-22
 */
@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {

    /**
     * 获取推荐文章列表
     * @return
     */
    List<RecommendVo> listSelect();

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVo> listRecommendVo();

    /**
     * 获取最热列表
     * @return
     */
    List<RecommendVo> listHotRead();
}
