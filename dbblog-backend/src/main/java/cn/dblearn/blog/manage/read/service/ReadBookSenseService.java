package cn.dblearn.blog.manage.read.service;

import cn.dblearn.blog.manage.read.entity.ReadBookSense;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 读后感 服务类
 * </p>
 *
 * @author bobbi
 * @since 2019-02-13
 */
public interface ReadBookSenseService extends IService<ReadBookSense> {

    /**
     * 获取读后感
     * @param bookId
     * @return
     */
    ReadBookSense getReadSense(Integer bookId);
}
