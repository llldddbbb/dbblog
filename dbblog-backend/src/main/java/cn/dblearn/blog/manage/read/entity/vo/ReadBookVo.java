package cn.dblearn.blog.manage.read.entity.vo;

import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.read.entity.ReadBook;
import lombok.Data;

import java.util.List;

/**
 * ReadBookVo
 *
 * @author bobbi
 * @date 2019/01/29 14:17
 * @email 571002217@qq.com
 * @description
 */
@Data
public class ReadBookVo extends ReadBook {
    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;
}
