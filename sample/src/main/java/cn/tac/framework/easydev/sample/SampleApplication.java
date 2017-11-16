package cn.tac.framework.easydev.sample;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * @author tac
 * @since 13/11/2017
 */
@SpringBootApplication
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class);
    }

    @Component
    static class MyBean implements InitializingBean {
        @Autowired
        private EasyCoreProperties easyCoreProperties;
        @Autowired
        private DaoCoreProperties daoCoreProperties;
        @Autowired
        private DaoCrudSupportProperties daoCrudSupportProperties;

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println(easyCoreProperties.getFormatPattern().getDatetime());
            System.out.println(daoCrudSupportProperties.isBoundary());
        }
    }
}
