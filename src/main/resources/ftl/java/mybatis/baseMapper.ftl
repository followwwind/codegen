package ${packageName!"com.wind.dao.base"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

/**
 * 数据库dao通用接口
 * @author wind
 * @param <R> 数据库表关联的实体类
 * @param <PK> 主键类型
 * @param <C> 条件
 */
public interface BaseMapper<R, PK, C> {
    /**
     * 添加纪录
     * @param r
     * @return
     */
    int insert(R r);

    /**
     * 通过id删除记录
     * @param id
     * @return
     */
    int deleteById(PK id);

    /**
     * 批量删除记录
     * @param r
     * @return
     */
    int deleteByCondition(R r);

    /**
     * 通过and拼接查询记录
     * @param id
     * @return
     */
    List<R> findEntitys(R r);

    /**
     * 条件批量查询记录
     * @param r
     * @return
     */
    List<R> findByCondition(C c);

    /**
     * 通过id更新记录
     * @param r
     * @return
     */
    int updateByCondition(R r);

    /**
     * 查询批量记录条数
     * @param r
     * @return
     */
    int countByCondition(C c);
}
