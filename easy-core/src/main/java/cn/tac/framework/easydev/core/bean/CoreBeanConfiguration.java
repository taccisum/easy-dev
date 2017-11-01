package cn.tac.framework.easydev.core.bean;

import cn.tac.framework.easydev.core.config.EasyDev;
import cn.tac.framework.easydev.core.constant.SpringProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author tac
 * @since 01/11/2017
 */
public class CoreBeanConfiguration {
    @Bean("__coreBeanConfiguration")
    @Profile({SpringProfiles.DEFAULT, SpringProfiles.DEV, SpringProfiles.TEST})
    public EasyDev EasyDev4DevAndTest() {
        EasyDev bean = new EasyDev();
        bean.getFormatPattern().setDate("yyyy-MM-dd");
        bean.getFormatPattern().setDatetime("yyyy-MM-dd HH:mm:ss");
        bean.setDebug(true);
        return bean;
    }

    @Bean("__coreBeanConfiguration")
    @Profile(SpringProfiles.PROD)
    public EasyDev EasyDev4Prod() {
        EasyDev bean = new EasyDev();
        bean.getFormatPattern().setDate("yyyy-MM-dd");
        bean.getFormatPattern().setDatetime("yyyy-MM-dd HH:mm:ss");
        bean.setDebug(false);
        return bean;
    }
}
