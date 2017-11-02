package cn.tac.framework.easydev.core.bean;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author tac
 * @since 01/11/2017
 */
public class CoreBeanConfiguration {
    @Bean
    public EasyCoreProperties easyCoreProperties4DevAndTest() {
        EasyCoreProperties bean = new EasyCoreProperties();
        bean.getFormatPattern().setDate("yyyy-MM-dd");
        bean.getFormatPattern().setDatetime("yyyy-MM-dd HH:mm:ss");
        return bean;
    }
}
