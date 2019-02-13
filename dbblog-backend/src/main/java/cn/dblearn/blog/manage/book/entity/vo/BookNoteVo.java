package cn.dblearn.blog.manage.book.entity.vo;

import cn.dblearn.blog.manage.book.entity.Book;
import cn.dblearn.blog.manage.book.entity.BookNote;
import cn.dblearn.blog.manage.operation.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleVo
 *
 * @author bobbi
 * @date 2019/01/09 16:51
 * @email 571002217@qq.com
 * @description 文章列表VO
 */
@Data
public class BookNoteVo extends BookNote {

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属书本
     */
    private Book book;

}
