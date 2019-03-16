package cn.dblearn.blog.search.mapper;

import cn.dblearn.blog.entity.article.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * ArticleRepository
 *
 * @author bobbi
 * @date 2019/03/13 15:00
 * @email 571002217@qq.com
 * @description
 */
@Component
public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {
}
