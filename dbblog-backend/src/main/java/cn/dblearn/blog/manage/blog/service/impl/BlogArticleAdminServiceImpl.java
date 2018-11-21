package cn.dblearn.blog.manage.blog.service.impl;

import cn.dblearn.blog.manage.blog.pojo.BlogArticle;
import cn.dblearn.blog.manage.blog.service.BlogArticleAdminService;
import cn.dblearn.blog.manage.blog.mapper.BlogArticleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * BlogArticleAdminServiceImpl
 *
 * @author bobbi
 * @date 2018/11/21 12:48
 * @email 571002217@qq.com
 * @description
 */
@Service
public class BlogArticleAdminServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleAdminService {
}
