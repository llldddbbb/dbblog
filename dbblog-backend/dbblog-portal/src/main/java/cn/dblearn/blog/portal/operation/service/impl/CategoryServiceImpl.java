package cn.dblearn.blog.portal.operation.service.impl;

import cn.dblearn.blog.entity.operation.Category;
import cn.dblearn.blog.mapper.operation.CategoryMapper;
import cn.dblearn.blog.portal.operation.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * CategoryServiceImpl
 *
 * @author bobbi
 * @date 2019/02/19 15:33
 * @email 571002217@qq.com
 * @description
 */
@Service("categoryPortalService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    /**
     * 获取categoryList
     *
     * @param params
     * @return
     */
    @Override
    public List<Category> listCategory(Map<String, Object> params) {
        String type = (String) params.get("type");
        String rank = (String) params.get("rank");
        return baseMapper.selectList(new QueryWrapper<Category>().lambda()
                .eq(StringUtils.isNotEmpty(type),Category::getType,type)
                .eq(StringUtils.isNotEmpty(rank),Category::getRank,rank));
    }
}
