package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.UpdatingServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.core.pojo.DTO;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingControllerSupport<E extends MinEntityStructureAware<PK>, PK, M extends DTO> extends ServiceAware<E, PK> {
    default void checkUpdatingService() {
        if (!(getService() instanceof UpdatingServiceSupport)) {
            //todo::
            throw new RuntimeException(getService().getClass() + " is not an instance of " + UpdatingServiceSupport.class);
        }
    }

    @PutMapping("{id}")
    @ApiOperation("根据id修改数据")
    RestfulApiResponse<E> update(@PathVariable("id") PK id, @RequestBody M entity);

    E convertUpdatingModel2Entity();
}
