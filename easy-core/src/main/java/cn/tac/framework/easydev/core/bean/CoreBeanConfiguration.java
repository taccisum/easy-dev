package cn.tac.framework.easydev.core.bean;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.constant.SpringProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author tac
 * @since 01/11/2017
 */
public class CoreBeanConfiguration {
    @Bean
    @Profile({SpringProfiles.DEFAULT, SpringProfiles.DEV, SpringProfiles.TEST})
    public EasyCoreProperties easyCoreProperties4DevAndTest() {
        EasyCoreProperties bean = new EasyCoreProperties();
        bean.getFormatPattern().setDate("yyyy-MM-dd");
        bean.getFormatPattern().setDatetime("yyyy-MM-dd HH:mm:ss");
        bean.setDebug(true);
        return bean;
    }

    @Bean
    @Profile(SpringProfiles.PROD)
    public EasyCoreProperties easyCoreProperties4Prod() {
        EasyCoreProperties bean = new EasyCoreProperties();
        bean.getFormatPattern().setDate("yyyy-MM-dd");
        bean.getFormatPattern().setDatetime("yyyy-MM-dd HH:mm:ss");
        bean.setDebug(false);
        return bean;
    }
}
