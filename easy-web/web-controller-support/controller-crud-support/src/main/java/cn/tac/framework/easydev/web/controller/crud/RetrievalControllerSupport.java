package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.service.crud.RetrievalServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.api.RetrievalServiceAware;
import cn.tac.framework.easydev.web.core.builder.SuccessRestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pojo.ListRequest;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrievalControllerSupport<E extends MinEntityStructureAware<PK>, PK>
        extends ServiceAware<E, PK>, RetrievalServiceAware<E, PK> {

    @GetMapping("{id}")
    @ApiOperation("根据id获取数据")
    default RestfulApiResponse<E> get(@PathVariable("id") PK id) {
        return new SuccessRestfulApiResponseBuilder<E>()
                .msg("获取数据成功")
                .data(getRetrievalService().selectByPrimaryKey(id))
                .build();
    }

    @GetMapping
    @ApiOperation("根据查询参数获取数据列表")
    default RestfulApiResponse<E> get(@ModelAttribute ListRequest request) {
        //todo::
        throw new NotImplementedException();
    }

    @Override
    default RetrievalServiceSupport<E, PK> getRetrievalService() {
        ServiceSkeleton<E, PK> service = getService();
        if (service instanceof RetrievalServiceSupport) {
            return (RetrievalServiceSupport<E, PK>) service;
        } else {
            throw new RuntimeException(service.getClass() + " is not an instance of " + RetrievalServiceSupport.class);
        }
    }
}
