package cn.tac.framework.easydev.sample.service;

import cn.tac.framework.easydev.sample.pojo.FooEntity;
import cn.tac.framework.easydev.sample.repo.FooRepository;
import cn.tac.framework.easydev.service.crud.CrudServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tac
 * @since 03/12/2017
 */
@Service
public class FooService extends CrudServiceSupport<FooEntity, String> {
    @Autowired
    public FooService(FooRepository repository) {
        super(repository);
    }
}
