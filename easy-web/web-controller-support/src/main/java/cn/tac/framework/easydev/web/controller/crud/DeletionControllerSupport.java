package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.DeletionServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface DeletionControllerSupport<E extends MinEntityStructureAware<PK>, PK> extends ServiceAware<E, PK> {
    default void checkDeletionService() {
        if (!(getService() instanceof DeletionServiceSupport)) {
            //todo::
            throw new RuntimeException(getService().getClass() + " is not an instance of " + DeletionServiceSupport.class);
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除数据")
    RestfulApiResponse<Integer> delete(@PathVariable("id") PK id);
}
