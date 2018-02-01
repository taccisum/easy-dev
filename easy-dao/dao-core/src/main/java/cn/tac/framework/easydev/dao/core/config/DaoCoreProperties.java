package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.dao.core.constant.DaoConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dao配置
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(DaoConstant.CONFIG_PROP_PREFIX)
public class DaoCoreProperties {
    private IdGeneratorProperties idGenerator;

    public IdGeneratorProperties getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGeneratorProperties idGenerator) {
        this.idGenerator = idGenerator;
    }

    public static class IdGeneratorProperties {
        private SnowFlakeProperties snowFlake;

        public SnowFlakeProperties getSnowFlake() {
            return snowFlake;
        }

        public void setSnowFlake(SnowFlakeProperties snowFlake) {
            this.snowFlake = snowFlake;
        }

        public static class SnowFlakeProperties {
            private Integer dataCenterId;
            private Integer workerId;

            public Integer getDataCenterId() {
                return dataCenterId;
            }

            public void setDataCenterId(Integer dataCenterId) {
                this.dataCenterId = dataCenterId;
            }

            public Integer getWorkerId() {
                return workerId;
            }

            public void setWorkerId(Integer workerId) {
                this.workerId = workerId;
            }
        }
    }
}
