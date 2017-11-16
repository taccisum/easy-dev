package cn.tac.framework.easydev.dao.crud;


import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用的mapper基类
 *
 * <p>
 *     所有基于mybatis编写的mapper都应实现自该接口，将由框架为派生的mapper实现通用的crud功能（无需.xml），
 *     具体有哪些方法可以参考{@link Mapper}的官方手册
 *     @see <a href="http://git.oschina.net/free/Mapper">http://git.oschina.net/free/Mapper</a>
 * </p>
 *
 * <p>
 *     若要扩展派生的mapper的方法，可以在派生的mapper中定义方法，然后在对应的.xml文件中实现statement即可。
 *     扩展的mapper方法粒度应较小，复用性较高，否则应考虑在dao层通过对mapper层方法的组合来实现所需功能
 * </p>
 *
 * <b>
 *     规定：所有的mapper均不能在程序开发中直接使用，而是应该通过派生自{@link CrudRepositorySupport}的类进行封装后，通过
 *     该派生类代理进行调用
 * </b>
 *
 * @author : tac
 * @since : 2017/11/2
 */
public interface CrudMapperSupport<E extends MinEntityStructure> extends
        Mapper<E>{
}
