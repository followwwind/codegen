package ${packageName!"com.wind.service.base"};

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
public interface BaseService<R, PK> {
    /**
     * 添加纪录
     * @param r
     * @return
     */
    int insert(R r);

    /**
     * 删除记录
     * @param r
     * @return
     */
    int delete(R r);
    
    /**
     * 批量单条记录
     * @param r
     * @return
     */
    R findEntity(R r);

    /**
     * 批量查询记录
     * @param r
     * @return
     */
    List<R> findList(R r);

    /**
     * 分页查询
     * @param r
     * @param page
     */
    void findPageList(R r, Page page);

    /**
     * 更新记录
     * @param r
     * @return
     */
    int update(R r);

    /**
     * 条件查询记录条数
     * @param r
     * @return
     */
    int count(R r);
}
