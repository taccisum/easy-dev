package cn.tac.framework.easydev.web.response.wrapper.controller;

import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.response.wrapper.annotation.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tac
 * @since 11/04/2018
 */
@RestController
@RequestMapping("foo")
public class FooController {
    @GenericResponse("获取成功")
    @GetMapping("test_wrapeer")
    public String test() {
        return "bar";
    }

    @GenericResponse("获取成功")
    @GetMapping("test_wrapper_when_response_rar")
    public RestfulApiResponse test1() {
        return RestfulApiResponseBuilder.success().build();
    }

    @GetMapping("test_normal_when_response_rar")
    public RestfulApiResponse test2() {
        return RestfulApiResponseBuilder.success().build();
    }

    @GetMapping("test_normal")
    public String test3() {
        return "bar1";
    }
}
