package com.wind.dao;

import java.util.List;

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
     * 通过id删除记录
     * @param id
     * @return
     */
    int deleteById(PK id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    R findById(PK id);

    /**
     * 通过条件查找
     * @param r
     * @return
     */
    List<R> findByCondition(R r);

    /**
     * 通过id更新记录
     * @param r
     * @return
     */
    int updateByCondition(R r);

    /**
     * 通过条件计算记录条数
     * @param r
     * @return
     */
    int countByCondition(R r);
}
