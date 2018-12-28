package cn.dblearn.blog.manage.operation.service.impl;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.common.util.Query;
import cn.dblearn.blog.manage.operation.entity.Category;
import cn.dblearn.blog.manage.operation.mapper.CategoryMapper;
import cn.dblearn.blog.manage.operation.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-12-17
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 分页查询分类
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<Category> page=baseMapper.selectPage(new Query<Category>(params).getPage(),
                new QueryWrapper<Category>().lambda().like(!StringUtils.isEmpty(name), Category::getName,name));
        return new PageUtils(page);
    }
}
