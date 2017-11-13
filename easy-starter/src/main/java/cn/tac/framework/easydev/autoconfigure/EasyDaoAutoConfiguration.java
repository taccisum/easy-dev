package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.util.IDUtils;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tac
 * @since 13/11/2017
 */
@Configuration
@EnableConfigurationProperties({DaoCoreProperties.class, DaoCrudSupportProperties.class})
public class EasyDaoAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RuntimeData4Dao runtimeData4Dao() {
        return () -> IDUtils.emptyUUID();
    }
}
