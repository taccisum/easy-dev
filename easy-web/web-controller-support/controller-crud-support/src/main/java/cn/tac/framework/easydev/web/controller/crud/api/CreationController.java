package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tac
 * @since 2.0
 */
public interface CreationController<E extends MinEntityStructureAware<PK>, PK, M> {
    @PostMapping
    RestfulApiResponse<E> insert(M model);
}
