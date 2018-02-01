package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author tac
 * @since 01/02/2018
 */
public interface UpdatingController<E extends MinEntityStructureAware<PK>, PK, M> {
    @PutMapping("{id}")
    RestfulApiResponse<E> update(PK id, M model);
}
