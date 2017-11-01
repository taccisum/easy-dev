package cn.tac.framework.easydev.core.app;

import cn.tac.framework.easydev.core.annotation.EnableEasyDevCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 供单元测试使用的class，特地放到app包下是为了不让扫描到任何一个bean
 *
 * @author tac
 * @since 01/11/2017
 */
@SpringBootApplication
@EnableEasyDevCore
public class Application4UnitTest {
}
