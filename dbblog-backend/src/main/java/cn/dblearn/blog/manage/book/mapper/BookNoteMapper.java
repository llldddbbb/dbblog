package cn.dblearn.blog.manage.book.mapper;

import cn.dblearn.blog.manage.book.entity.BookNote;
import cn.dblearn.blog.manage.book.entity.vo.BookNoteVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 笔记 Mapper 接口
 * </p>
 *
 * @author bobbi
 * @since 2019-02-13
 */
@Mapper
public interface BookNoteMapper extends BaseMapper<BookNote> {

    /**
     * 查询列表
     *
     * @param page
     * @param params
     * @return
     */
    List<BookNoteVo> listBookNoteVo(Page<BookNoteVo> page, @Param("bookNote") Map<String, Object> params);
}
