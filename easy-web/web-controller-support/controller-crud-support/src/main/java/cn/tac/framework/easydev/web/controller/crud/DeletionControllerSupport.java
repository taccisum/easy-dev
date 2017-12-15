package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.service.crud.DeletionServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.api.DeletionServiceAware;
import cn.tac.framework.easydev.web.core.builder.SuccessRestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletionControllerSupport<E extends MinEntityStructureAware<PK>, PK>
        extends ServiceAware<E, PK>, DeletionServiceAware<E, PK> {
    @DeleteMapping("{id}")
    @ApiOperation("删除数据")
    default RestfulApiResponse<Integer> delete(@PathVariable("id") PK id) {
        return new SuccessRestfulApiResponseBuilder<Integer>()
                .msg("删除数据成功")
                .data(getDeletionService().deleteByPrimaryKey(id))
                .build();
    }

    @Override
    default DeletionServiceSupport<E, PK> getDeletionService() {
        ServiceSkeleton<E, PK> service = getService();
        if (service instanceof DeletionServiceSupport) {
            return (DeletionServiceSupport<E, PK>) service;
        } else {
            throw new RuntimeException(service.getClass() + " is not an instance of " + DeletionServiceSupport.class);
        }
    }
}
