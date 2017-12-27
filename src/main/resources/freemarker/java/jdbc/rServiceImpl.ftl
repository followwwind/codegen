package com.wind.service.impl;

import java.util.List;

import com.wind.dao.CarDao;
import com.wind.dao.CarOrderDao;
import com.wind.dao.impl.CarDaoImpl;
import com.wind.dao.impl.CarOrderDaoImpl;
import com.wind.entity.Car;
import com.wind.entity.CarOrder;
import com.wind.service.CarOrderService;
import com.wind.service.base.BaseServiceImpl;
import com.wind.util.StringUtil;

public class ${property}ServiceImpl extends BaseServiceImpl<${property}, String> implements ${property}Service{

    private ${property}Dao dao = new ${property}DaoImpl();


    @Override
    public int insert(${property} r) {
        int i = dao.insert(r);
        return i;
    }

    @Override
    public int deleteById(String id) {
        return dao.deleteById(id);
    }

    @Override
    public ${property} findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<${property}> findByCondition(${property} r) {
        return dao.findByCondition(r);
    }

    @Override
    public int updateByCondition(${property} r) {
        return dao.updateConditionById(r);
    }


}
