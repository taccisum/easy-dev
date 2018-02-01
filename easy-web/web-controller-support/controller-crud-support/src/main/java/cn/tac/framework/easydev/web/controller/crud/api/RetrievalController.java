package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tac
 * @since 01/02/2018
 */
public interface RetrievalController<E extends MinEntityStructureAware<PK>, PK> {
    @GetMapping("{id}")
    RestfulApiResponse<E> get(PK id);

//    @GetMapping
//    RestfulApiResponse<E> get(ListRequest request);
}
