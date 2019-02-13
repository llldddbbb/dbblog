package cn.dblearn.blog.manage.book.entity.dto;

import cn.dblearn.blog.manage.book.entity.BookNote;
import cn.dblearn.blog.manage.operation.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * BookNote
 *
 * @author bobbi
 * @date 2019/01/08 19:04
 * @email 571002217@qq.com
 * @description
 */
@Data
public class BookNoteDto extends BookNote {

    private List<Tag> tagList;

}
