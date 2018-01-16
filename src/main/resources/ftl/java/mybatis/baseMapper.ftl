package com.wind.dao.base;

import java.util.List;
import com.wind.entity.base.Page;

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
     * 通过id查询单条记录
     * @param id
     * @return
     */
    R findById(PK id);

    /**
     * 条件批量查询记录
     * @param r
     * @return
     */
    List<R> findByCondition(R r);

    /**
     * 分页查询记录
     * @param r
     * @return
     */
    List<R> findPageList(R r);

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
    int countByCondition(R r);
}
