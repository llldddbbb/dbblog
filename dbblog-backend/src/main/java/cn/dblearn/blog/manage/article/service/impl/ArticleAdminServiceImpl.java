package cn.dblearn.blog.manage.article.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.article.entity.ArticleTagLink;
import cn.dblearn.blog.manage.article.mapper.ArticleMapper;
import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.article.mapper.ArticleTagLinkMapper;
import cn.dblearn.blog.manage.article.mapper.ArticleTagMapper;
import cn.dblearn.blog.manage.article.service.ArticleAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * BlogArticleAdminServiceImpl
 *
 * @author bobbi
 * @date 2018/11/21 12:48
 * @email 571002217@qq.com
 * @description
 */
@Service
public class ArticleAdminServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleAdminService {

    @Autowired
    private ArticleTagLinkMapper blogArticleTagMapper;

    @Autowired
    private ArticleTagMapper blogTagMapper;
    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Article> page=baseMapper.selectPage(new Query<Article>(params).getPage(),
                new QueryWrapper<Article>().lambda().like(!StringUtils.isEmpty(title), Article::getTitle,title));
        return new PageUtils(page);
    }

    /**
     * 保存博文文章
     *
     * @param blogArticle
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(Article blogArticle) {
        baseMapper.insert(blogArticle);
        this.saveDiyTag(blogArticle);
    }

    /**
     * 更新博文
     *
     * @param blogArticle
     */
    @Override
    public void updateArticle(Article blogArticle) {
        // 删除多对多所属标签
        blogArticleTagMapper.delete(new QueryWrapper<ArticleTagLink>().lambda()
            .eq(ArticleTagLink::getArticleId,blogArticle.getArticleId()));
        // 更新所属标签
        this.saveDiyTag(blogArticle);
        // 更新博文
        baseMapper.updateById(blogArticle);

    }

    /**
     * 添加自定义标签
     * @param blogArticle
     */
    private void saveDiyTag(Article blogArticle){
        if(!CollectionUtils.isEmpty(blogArticle.getTagList())){
            blogArticle.getTagList().forEach(tag -> {
                if(tag.getTagId() == null) {
                    // 添加进tag标签
                    tag.setCategoryId(blogArticle.getCategoryId());
                    tag.setOrientationId(blogArticle.getOrientationId());
                    blogTagMapper.insert(tag);
                }
                ArticleTagLink blogArticleTag=new ArticleTagLink(blogArticle.getArticleId(),tag.getTagId());
                blogArticleTagMapper.insert(blogArticleTag);
            });
        }
    }

    /**
     * 批量删除
     *
     * @param articleIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] articleIds) {
        //先删除博文标签多对多关联
        Arrays.stream(articleIds).forEach(articleId -> {
            blogArticleTagMapper.delete(new QueryWrapper<ArticleTagLink>().lambda()
                    .eq(articleId!=null, ArticleTagLink::getArticleId,articleId));
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }


}
