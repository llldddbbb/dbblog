package cn.dblearn.blog.manage.read.entity.dto;

import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.read.entity.ReadBook;
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
public class ReadBookDto extends ReadBook {

    List<Tag> tagList;
}
