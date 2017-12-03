package cn.tac.framework.easydev.sample.repo;

import cn.tac.framework.easydev.dao.crud.CrudRepositorySupport;
import cn.tac.framework.easydev.sample.mapper.FooMapper;
import cn.tac.framework.easydev.sample.pojo.FooEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author tac
 * @since 03/12/2017
 */
@Repository
public class FooRepository extends CrudRepositorySupport<FooEntity, String> {
    @Autowired
    public FooRepository(FooMapper mapper) {
        super(mapper, FooEntity.class);
    }
}
