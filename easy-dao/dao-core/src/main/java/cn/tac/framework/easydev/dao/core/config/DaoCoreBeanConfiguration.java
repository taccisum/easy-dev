package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.dao.core.bean.DefaultRuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author tac
 * @since 01/11/2017
 */
public class DaoCoreBeanConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RuntimeData4Dao runtimeData4Dao() {
        return new DefaultRuntimeData4Dao();
    }

    @Bean
    public DaoCoreProperties daoCoreProperties() {
        DaoCoreProperties bean = new DaoCoreProperties();
        return bean;
    }
}
