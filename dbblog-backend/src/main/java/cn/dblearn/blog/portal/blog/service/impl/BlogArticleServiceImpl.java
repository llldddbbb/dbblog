package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.portal.blog.pojo.entity.BlogArticle;
import cn.dblearn.blog.portal.blog.mapper.BlogArticleMapper;
import cn.dblearn.blog.portal.blog.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

}
