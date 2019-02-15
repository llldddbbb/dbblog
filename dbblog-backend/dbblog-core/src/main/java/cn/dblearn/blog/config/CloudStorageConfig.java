package cn.dblearn.blog.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * CloudStorageConfig
 *
 * @author bobbi
 * @date 2018/10/19 18:47
 * @email 571002217@qq.com
 * @description 云存储配置类
 */
@Data
@Configuration
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Value("${oss.qiniu.domain}")
    private String qiniuDomain;
    /**
     * 七牛路径前缀
     */
    @Value("${oss.qiniu.prefix}")
    private String qiniuPrefix;
    /**
     * 七牛ACCESS_KEY
     */
    @Value("${oss.qiniu.accessKey}")
    private String qiniuAccessKey;
    /**
     * 七牛SECRET_KEY
     */
    @Value("${oss.qiniu.secretKey}")
    private String qiniuSecretKey;
    /**
     * 七牛空间名
     */
    @Value("${oss.qiniu.bucketName}")
    private String qiniuBucketName;
}
