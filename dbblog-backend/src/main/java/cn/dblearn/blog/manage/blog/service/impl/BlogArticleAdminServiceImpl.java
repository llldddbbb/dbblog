package cn.dblearn.blog.manage.blog.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.blog.entity.BlogArticleTag;
import cn.dblearn.blog.manage.blog.mapper.BlogArticleMapper;
import cn.dblearn.blog.manage.blog.entity.BlogArticle;
import cn.dblearn.blog.manage.blog.mapper.BlogArticleTagMapper;
import cn.dblearn.blog.manage.blog.service.BlogArticleAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
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
public class BlogArticleAdminServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleAdminService {

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;
    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogArticle> page=baseMapper.selectPage(new Query<BlogArticle>(params).getPage(),
                new QueryWrapper<BlogArticle>());
        return new PageUtils(page);
    }

    /**
     * 保存博文文章
     *
     * @param blogArticle
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(BlogArticle blogArticle) {
        baseMapper.insert(blogArticle);
        if(!CollectionUtils.isEmpty(blogArticle.getTagList())){
            blogArticle.getTagList().forEach(tag -> {
                BlogArticleTag blogArticleTag=new BlogArticleTag(blogArticle.getArticleId(),tag.getTagId());
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
            blogArticleTagMapper.delete(new QueryWrapper<BlogArticleTag>().lambda()
                    .eq(articleId!=null,BlogArticleTag::getArticleId,articleId));
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }
}
