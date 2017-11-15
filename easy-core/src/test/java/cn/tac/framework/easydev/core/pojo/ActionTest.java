package cn.tac.framework.easydev.core.pojo;

import org.junit.Test;

/**
 * @author tac
 * @since 16/11/2017
 */
public class ActionTest {
    @Test
    public void doAction() throws Exception {
        doGreetAction(() -> System.out.println("hello world"));
    }

    private void doGreetAction(Action action) {
        action.doAction();
    }
}
