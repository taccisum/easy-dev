package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.service.crud.RetrievalServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.pojo.ListRequest;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface RetrievalControllerSupport<E extends MinEntityStructure<PK>, PK> extends ServiceAware<E, PK> {
    default void checkRetrievalService() {
        if (!(getService() instanceof RetrievalServiceSupport)) {
            //todo::
            throw new RuntimeException(getService().getClass() + " is not an instance of " + RetrievalServiceSupport.class);
        }
    }

    @GetMapping("{id}")
    @ApiOperation("根据id获取数据")
    RestfulApiResponse<E> get(@PathVariable("id") PK id);


    @GetMapping
    @ApiOperation("根据查询参数获取数据列表")
    RestfulApiResponse<E> get(@ModelAttribute ListRequest request);
}
