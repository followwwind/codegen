package ${packageName!"com.wind.service.base"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

/**
 * @Title: BaseService
 * @Package ${packageName!"com.wind.service.base"}
 * @Description: 基础service增删查改服务
 * @author wind
 * @date .now?string("yyyy/MM/dd HH:mm:ss")
 * @version V1.0
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
