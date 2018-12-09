package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.common.pojo.Result;
import cn.dblearn.blog.manage.blog.mapper.BlogArticleMapper;
import cn.dblearn.blog.portal.blog.pojo.vo.BlogArticleVo;
import cn.dblearn.blog.portal.blog.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HomeServiceImpl
 *
 * @author bobbi
 * @date 2018/11/07 20:37
 * @email 571002217@qq.com
 * @description
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    /**
     * 获取首页数据
     *
     * @return
     */
    @Override
    public Result listHomeData() {
        List<BlogArticleVo> articleVoList=blogArticleMapper.listHomeArticle(null);
        return Result.ok().put("articleList",articleVoList);
    }
}
