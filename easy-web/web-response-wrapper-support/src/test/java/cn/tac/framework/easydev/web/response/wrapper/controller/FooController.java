package cn.tac.framework.easydev.web.response.wrapper.controller;

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
    @GetMapping("bar")
    public String bar(){
        return "bar";
    }
}
