package cn.tac.framework.easydev.sample.mapper;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.sample.pojo.FooEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tac
 * @since 03/12/2017
 */
@Mapper
public interface FooMapper extends CrudMapperSupport<FooEntity> {
}
