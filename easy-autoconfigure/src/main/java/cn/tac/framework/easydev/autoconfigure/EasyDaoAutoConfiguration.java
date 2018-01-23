package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.util.IDUtils;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.config.AutoConfigureConditionalClass;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
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
@EnableConfigurationProperties({DaoCoreProperties.class, DaoCrudSupportProperties.class})
@Import(value = {EntityUtils.class, EasyCoreAutoConfiguration.class})
@ConditionalOnClass(AutoConfigureConditionalClass.class)
public class EasyDaoAutoConfiguration {
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
}
