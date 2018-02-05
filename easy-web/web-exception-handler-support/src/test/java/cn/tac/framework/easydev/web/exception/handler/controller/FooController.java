package cn.tac.framework.easydev.web.exception.handler.controller;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.exception.handler.exception.FooException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tac
 * @since 2.0
 */
@RestController
@RequestMapping("foo")
public class FooController {
    @GetMapping("error")
    public static RestfulApiResponse error() {
        throw new IllegalArgumentException();
    }

    @GetMapping("failure")
    public static RestfulApiResponse failure() {
        throw new FooException("出错啦");
    }
}
