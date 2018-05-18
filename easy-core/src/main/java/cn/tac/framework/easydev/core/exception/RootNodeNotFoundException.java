package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 2.3
 */
public class RootNodeNotFoundException extends RuntimeException {
    public <PK> RootNodeNotFoundException(PK rootId) {
        super("root id: " +rootId);
    }
}
