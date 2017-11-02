package cn.tac.framework.easydev.dao.crud.config;

import org.springframework.context.annotation.Bean;

/**
 * @author tac
 * @since 02/11/2017
 */
public class DaoCrudBeanConfiguration {
    @Bean
    public DaoCrudSupport daoCrudSupport() {
        DaoCrudSupport bean = new DaoCrudSupport();
        bean.setBoundaryDefault(true);
        return bean;
    }
}
