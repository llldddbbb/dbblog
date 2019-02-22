package cn.dblearn.blog.portal.book.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.book.BookNote;
import cn.dblearn.blog.entity.book.vo.BookNoteVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * BookNoteAdminService
 *
 * @author bobbi
 * @date 2018/11/21 12:47
 * @email 571002217@qq.com
 * @description
 */
public interface BookNoteService extends IService<BookNote> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取简单对象
     * @param linkId
     * @return
     */
    BookNoteVo getSimpleBookNoteVo(Integer linkId);
}
