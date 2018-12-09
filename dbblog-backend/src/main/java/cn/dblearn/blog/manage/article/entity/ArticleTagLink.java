package cn.dblearn.blog.manage.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章标签多对多维护表
 * </p>
 *
 * @author bobbi
 * @since 2018-11-23
 */
@Data
@ApiModel(value="BlogArticleTag对象", description="文章标签多对多维护表")
public class ArticleTagLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer articleId;

    private Integer tagId;

    public ArticleTagLink(Integer articleId, Integer tagId){
        this.articleId = articleId;
        this.tagId = tagId;
    }


}
