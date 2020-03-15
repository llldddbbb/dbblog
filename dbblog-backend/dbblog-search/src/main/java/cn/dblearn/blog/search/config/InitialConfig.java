package cn.dblearn.blog.search.config;

import cn.dblearn.blog.common.constants.RabbitMqConstants;
import cn.dblearn.blog.common.util.RabbitMqUtils;
import com.rabbitmq.client.ConnectionFactory;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * InitialConfig
 *
 * @author bobbi
 * @date 2019/03/16 23:04
 * @email 571002217@qq.com
 * @description
 */
@Configuration
@ConditionalOnBean(ElasticsearchClient.class)
public class InitialConfig {

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    /**
     * 项目启动时重新导入索引
     */
    @PostConstruct
    public void initEsIndex(){
        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE,"dbblog-search init index");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setAutomaticRecoveryEnabled(false);
    }
}
