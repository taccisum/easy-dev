package cn.tac.framework.easydev.web.messageconverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tac
 * @since 2.0
 */
public interface ObjectMapperBuilder {
    ObjectMapper build();
}
