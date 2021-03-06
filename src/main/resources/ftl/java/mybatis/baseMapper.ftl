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
 */
public interface BaseMapper<R, PK> {

    /**
     * 添加纪录
     * @param r
     * @return
     */
    int save(R r);

    /**
     * 批量删除记录
     * @param id
     * @return
     */
    int deleteById(PK id);

    /**
     * 通过and拼接查询记录
     * @param id
     * @return
     */
    R findById(PK id);

    /**
     * 条件批量查询记录
     * @param r
     * @return
     */
    List<R> findList(R r);

    /**
     * 通过id更新记录
     * @param r
     * @return
     */
    int update(R r);

    /**
     * 查询批量记录条数
     * @param r
     * @return
     */
    int count(R r);
}
