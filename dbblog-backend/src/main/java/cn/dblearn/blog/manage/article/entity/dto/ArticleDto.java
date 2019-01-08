package cn.dblearn.blog.manage.article.entity.dto;

import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.operation.entity.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleDto
 *
 * @author bobbi
 * @date 2019/01/08 19:04
 * @email 571002217@qq.com
 * @description
 */
@Data
public class ArticleDto extends Article {

    private List<Tag> tagList;

}
