package cn.tac.framework.easydev.sample;

import cn.tac.framework.easydev.sample.service.FooService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author tac
 * @since 2.0
 */
@SpringBootApplication
public class SimpleApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .profiles("memdb", "simple")
                .sources(SimpleApplication.class)
                .listeners(after())
                .run(args);
    }

    private static ApplicationListener<ApplicationReadyEvent> after() {
        return readyEvent -> System.out.println(readyEvent.getApplicationContext().getBean(FooService.class).selectAll());
    }
}
