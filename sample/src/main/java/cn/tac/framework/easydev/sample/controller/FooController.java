package cn.tac.framework.easydev.sample.controller;

import cn.tac.framework.easydev.sample.pojo.FooEntity;
import cn.tac.framework.easydev.sample.pojo.dto.FooModel;
import cn.tac.framework.easydev.sample.service.FooService;
import cn.tac.framework.easydev.web.controller.crud.CrudControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tac
 * @since 22/12/2017
 */
@RestController
@RequestMapping("foo")
public class FooController extends CrudControllerSupport<FooEntity, String, FooModel, FooModel> {
    @Autowired
    public FooController(FooService service) {
        super(service);
    }

    @Override
    public FooEntity convertCreationModel2Entity(FooModel model) {
        FooEntity entity = new FooEntity();
        entity.setBar1(model.getBar1());
        return entity;
    }

    @Override
    public FooEntity convertUpdatingModel2Entity(String id, FooModel model) {
        FooEntity entity = convertCreationModel2Entity(model);
        entity.setId(id);
        return entity;
    }
}
