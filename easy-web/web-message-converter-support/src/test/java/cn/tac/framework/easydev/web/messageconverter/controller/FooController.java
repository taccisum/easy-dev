package cn.tac.framework.easydev.web.messageconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author tac
 * @since 2.0
 */
@RestController
@RequestMapping("foo")
public class FooController {
    @GetMapping("index")
    public Foo index(){
        //noinspection deprecation
        return new Foo(157928765366870016L, new Date(117, 0, 1, 12, 34, 56));
    }

    public static class Foo {
        public Foo() {
        }

        public Foo(Long longValue, Date dateValue) {
            this.longValue = longValue;
            this.dateValue = dateValue;
        }

        private Long longValue;
        private Date dateValue;

        public Long getLongValue() {
            return longValue;
        }

        public void setLongValue(Long longValue) {
            this.longValue = longValue;
        }

        public Date getDateValue() {
            return dateValue;
        }

        public void setDateValue(Date dateValue) {
            this.dateValue = dateValue;
        }
    }
}
