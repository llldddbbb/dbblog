package cn.dblearn.blog.portal.blog.service.impl;

import cn.dblearn.blog.portal.blog.pojo.entity.BlogTag;
import cn.dblearn.blog.portal.blog.mapper.BlogTagMapper;
import cn.dblearn.blog.portal.blog.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-11-07
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
