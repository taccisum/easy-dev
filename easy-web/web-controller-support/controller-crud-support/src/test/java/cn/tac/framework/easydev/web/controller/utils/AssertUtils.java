package cn.tac.framework.easydev.web.controller.utils;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author tac
 * @since 2.0
 */
public abstract class AssertUtils {
    public static void assertSuccess(RestfulApiResponse response){
        assertThat(response.getState()).isEqualTo(RestfulApiResponse.SUCCESS_STATE);
        assertThat(response.getCode()).isEqualTo(AppCode.SUCCESS_CODE);
    }
}
