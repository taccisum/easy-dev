package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.dao.DaoCrudSupportAutoConfiguration;
import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.util.IDUtils;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.core.strategy.id.SnowFlakeIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@EnableConfigurationProperties({DaoCoreProperties.class})
@Import(value = {EntityUtils.class, EasyCoreAutoConfiguration.class, DaoCrudSupportAutoConfiguration.class})
@ConditionalOnClass(DaoCoreProperties.class)
public class EasyDaoAutoConfiguration {
    private Logger logger = LoggerFactory.getLogger(EasyDaoAutoConfiguration.class);
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    @ConditionalOnMissingBean
    public RuntimeData4Dao runtimeData4Dao(EasyCoreProperties easyCoreProperties) {
        return new RuntimeData4Dao() {
            @Override
            public String userId() {
                return IDUtils.emptyUUID(easyCoreProperties.isCompactUUID());
            }

            @Override
            public String organizationId() {
                return IDUtils.emptyUUID(easyCoreProperties.isCompactUUID());
            }
        };
    }

    @Bean
    public SnowFlakeIDGenerator snowFlakeIDGenerator(DaoCoreProperties properties){
        logger.debug("配置SnowFlake ID生成器");
        if (properties.getIdGenerator() == null || properties.getIdGenerator().getSnowFlake() == null) {
            logger.warn("未指定data center id和worker id，在正确配置前" + SnowFlakeIDGenerator.class + "将无法正常使用");
            return null;
        }
        SnowFlakeIDGenerator.setProperties(properties);
        return (SnowFlakeIDGenerator) SnowFlakeIDGenerator.instance();
    }
}
