package cn.dblearn.blog.manage.article.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.article.entity.dto.ArticleDto;
import cn.dblearn.blog.manage.article.entity.vo.ArticleVo;
import cn.dblearn.blog.manage.operation.entity.Category;
import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.operation.entity.TagLink;
import cn.dblearn.blog.manage.article.mapper.ArticleMapper;
import cn.dblearn.blog.manage.article.entity.Article;
import cn.dblearn.blog.manage.operation.mapper.TagLinkMapper;
import cn.dblearn.blog.manage.article.service.ArticleService;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import cn.dblearn.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * articleAdminServiceImpl
 *
 * @author bobbi
 * @date 2018/11/21 12:48
 * @email 571002217@qq.com
 * @description
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagLinkMapper tagLinkMapper;

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ArticleVo> page = new Query<ArticleVo>(params).getPage();
        List<ArticleVo> articleList = baseMapper.listArticleVo(params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(null);
        // 封装ArticleVo
        articleList.forEach(articleVo -> {
            // 根据类别Id数组查询类别数组
            String categoryStr = renderCategoryArr(articleVo.getCategoryId(),categoryList);
            // 设置类别
            articleVo.setCategoryListStr(categoryStr);
            // 根据文章Id查询标签列表
            List<Tag> tagList = tagService.listByArticleId(articleVo.getArticleId());
            // 设置标签列表
            articleVo.setTagList(tagList);
        });
        page.setRecords(articleList);
        return new PageUtils(page);
    }

    /**
     * 根据类别Id数组查询类别数组
     * @param categoryIds
     * @param categoryList
     * @return
     */
    private String renderCategoryArr(String categoryIds, List<Category> categoryList) {
        if (StringUtils.isEmpty(categoryIds)) {
            return "";
        }
        List<String> categoryStrList = new ArrayList<>();
        String[] categoryIdArr = categoryIds.split(",");
        for (int i = 0; i < categoryIdArr.length; i++) {
            Integer categoryId = Integer.parseInt(categoryIdArr[i]);
            // 根据Id查找类别名称
            String categoryStr = categoryList.stream()
                    .filter(category -> category.getId().equals(categoryId))
                    .map(Category::getName)
                    .findAny().get();
            categoryStrList.add(categoryStr);
        }
        return String.join(",",categoryStrList);

    }

    /**
     * 保存博文文章
     *
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDto article) {
        baseMapper.insert(article);
        this.saveTagAndNew(article);
    }

    /**
     * 更新博文
     *
     * @param article
     */
    @Override
    public void updateArticle(ArticleDto article) {
        // 删除多对多所属标签
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                .eq(TagLink::getForeignId,article.getArticleId()));
        // 更新所属标签
        this.saveTagAndNew(article);
        // 更新博文
        baseMapper.updateById(article);

    }

    /**
     * 获取文章对象
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleDto getArticle(Integer articleId) {
        ArticleDto articleDto = new ArticleDto();
        Article article = this.baseMapper.selectById(articleId);
        BeanUtils.copyProperties(article,articleDto);
        // 查询所属标签
        articleDto.setTagList(tagService.listByArticleId(articleId));
        return articleDto;
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
            tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                    .eq(articleId!=null, TagLink::getForeignId,articleId));
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }

    /**
     * 添加文章所属标签，包含新增标签
     * @param article
     */
    private void saveTagAndNew(ArticleDto article){
        if(!CollectionUtils.isEmpty(article.getTagList())){
            article.getTagList().forEach(tag -> {
                if(tag.getTagId() == null) {
                    tagService.save(tag);
                }
                TagLink articleTagLink=new TagLink(article.getArticleId(),tag.getTagId());
                tagLinkMapper.insert(articleTagLink);
            });
        }
    }


}
