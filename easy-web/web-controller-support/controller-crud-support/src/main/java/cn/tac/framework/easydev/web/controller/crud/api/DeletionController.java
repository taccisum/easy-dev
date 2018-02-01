package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletionController<E extends MinEntityStructureAware<PK>, PK> {
    @DeleteMapping("{id}")
    RestfulApiResponse<Integer> delete(PK id);
}
