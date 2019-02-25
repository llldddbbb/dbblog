package cn.dblearn.blog.portal.operation.service.impl;

import cn.dblearn.blog.common.constants.RedisKeyConstants;
import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.entity.article.vo.ArticleVo;
import cn.dblearn.blog.entity.book.vo.BookNoteVo;
import cn.dblearn.blog.entity.operation.Recommend;
import cn.dblearn.blog.entity.operation.vo.RecommendVo;
import cn.dblearn.blog.manage.operation.mapper.RecommendMapper;
import cn.dblearn.blog.portal.article.service.ArticleService;
import cn.dblearn.blog.portal.book.service.BookNoteService;
import cn.dblearn.blog.portal.operation.service.RecommendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * RecommendServiceImpl
 *
 * @author bobbi
 * @date 2019/02/22 13:42
 * @email 571002217@qq.com
 * @description
 */
@Service("recommendPortalService")
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Resource
    private ArticleService articleService;

    @Resource
    private BookNoteService bookNoteService;




    @Override
    @Cacheable(value = RedisKeyConstants.PORTAL_RECOMMEND_LIST)
    public List<RecommendVo> listRecommendVo() {
        List<RecommendVo> recommendList =this.baseMapper.listRecommendVo();
        return genRecommendList(recommendList);
    }

    @Override
    public List<RecommendVo> listHotRead() {
        List<RecommendVo> hotReadList =this.baseMapper.listHotRead();
        genRecommendList(hotReadList);
        hotReadList.get(0).setTop(true);
        return hotReadList;
    }

    private List<RecommendVo> genRecommendList(List<RecommendVo> recommendList) {
        recommendList.forEach(recommendVo -> {
            if(ModuleEnum.ARTICLE.getValue() == recommendVo.getType()){
                ArticleVo simpleArticleVo = articleService.getSimpleArticleVo(recommendVo.getLinkId());
                BeanUtils.copyProperties(simpleArticleVo,recommendVo);
                recommendVo.setUrlType("article");
            }else if(ModuleEnum.BOOK_NOTE.getValue() == recommendVo.getType()) {
                BookNoteVo simpleBookNoteVo = bookNoteService.getSimpleBookNoteVo(recommendVo.getLinkId());
                recommendVo.setUrlType("bookNote");
                BeanUtils.copyProperties(simpleBookNoteVo,recommendVo);
            }
        });
        return recommendList;
    }
}
