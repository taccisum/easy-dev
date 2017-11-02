package cn.tac.framework.easydev.dao.crud.config;

import org.springframework.context.annotation.Bean;

/**
 * @author tac
 * @since 02/11/2017
 */
public class DaoCrudBeanConfiguration {
    @Bean
    public DaoCrudSupportProperties daoCrudSupport() {
        DaoCrudSupportProperties bean = new DaoCrudSupportProperties();
        bean.setBoundaryDefault(true);
        return bean;
    }
}
