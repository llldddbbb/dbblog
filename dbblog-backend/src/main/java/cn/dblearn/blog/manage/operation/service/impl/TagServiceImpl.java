package cn.dblearn.blog.manage.operation.service.impl;

import cn.dblearn.blog.common.enums.ModuleEnum;
import cn.dblearn.blog.manage.operation.entity.Tag;
import cn.dblearn.blog.manage.operation.mapper.TagMapper;
import cn.dblearn.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TagServiceImpl
 *
 * @author bobbi
 * @date 2019/01/08 16:21
 * @email 571002217@qq.com
 * @description
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper,Tag> implements TagService {
    /**
     * 根据articleId获取Tag列表
     *
     * @param articleId
     * @return
     */
    @Override
    public List<Tag> listByArticleId(Integer articleId) {
        return this.baseMapper.listByForeignId(articleId, ModuleEnum.ARTICLE.getValue());
    }
}
