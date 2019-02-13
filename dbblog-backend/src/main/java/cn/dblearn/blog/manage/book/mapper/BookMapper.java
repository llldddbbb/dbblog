package cn.dblearn.blog.manage.book.mapper;

import cn.dblearn.blog.manage.book.entity.Book;
import cn.dblearn.blog.manage.book.entity.vo.BookVo;
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
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 获取bookVo
     * @param page
     * @param params
     * @return
     */
    List<BookVo> listBookVo(Page<BookVo> page, @Param("params") Map<String, Object> params);
}
