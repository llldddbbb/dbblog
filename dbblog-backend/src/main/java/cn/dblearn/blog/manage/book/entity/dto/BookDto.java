package cn.dblearn.blog.manage.book.entity.dto;

import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.book.entity.Book;
import lombok.Data;

import java.util.List;

/**
 * ReadBookDto
 *
 * @author bobbi
 * @date 2019/01/28 16:44
 * @email 571002217@qq.com
 * @description
 */
@Data
public class BookDto extends Book {

    List<Tag> tagList;
}
