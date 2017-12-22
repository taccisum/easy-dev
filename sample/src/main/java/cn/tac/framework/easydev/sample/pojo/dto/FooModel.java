package cn.tac.framework.easydev.sample.pojo.dto;

import cn.tac.framework.easydev.web.core.pojo.DTO;

/**
 * @author tac
 * @since 22/12/2017
 */
public class FooModel implements DTO {
    private String bar1;

    public String getBar1() {
        return bar1;
    }

    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }
}
