package cn.tac.framework.easydev.core.pojo;

import org.junit.Test;

/**
 * @author tac
 * @since 2.0
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
