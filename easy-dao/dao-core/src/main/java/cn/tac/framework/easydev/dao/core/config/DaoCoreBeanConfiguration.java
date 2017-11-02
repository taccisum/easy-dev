package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.core.constant.SpringProfiles;
import cn.tac.framework.easydev.dao.core.bean.DefaultRuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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
    @Profile({SpringProfiles.DEFAULT, SpringProfiles.DEV, SpringProfiles.TEST})
    public DaoCore EasyDev4DevAndTest() {
        DaoCore bean = new DaoCore();
        return bean;
    }

    @Bean
    @Profile(SpringProfiles.PROD)
    public DaoCore EasyDev4Prod() {
        DaoCore bean = new DaoCore();
        return bean;
    }
}
