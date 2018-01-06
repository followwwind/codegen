package com.wind.dao;

import java.util.List;
import com.wind.dao.${property}Dao;
import com.wind.dao.impl.${property}DaoImpl;
import com.wind.entity.${property};
import com.wind.entity.base.Page;


/**
 * ${remarks!""} dao测试
 * @author wind
 */
public class ${property}DaoTest{

    ${property}Dao dao = new ${property}DaoImpl();

    @Test
    public void insert() {
        ${property} entity = new ${property}();
        dao.insert(entity);
    }

    @Test
    public void deleteById() {
        String id = "";
        dao.deleteById(id);
    }

    @Test
    public void findById() {
        String id = "";
        ${property} entity = dao.findById(id);
        System.out.println(entity);
    }

    @Test
    public void findByCondition() {
        ${property} entity = new ${property}();
        List<${property}> entitys = dao.findByCondition(entity);
        System.out.println(entitys.size());
    }

    @Test
    public void findPageList(){
        ${property} entity = new ${property}();
        Page page = new Page();
        dao.findPageList(entity, page);
        System.out.println(page);
    }

    @Test
    public void updateByCondition() {
        ${property} entity = new ${property}();
        dao.updateByCondition(entity);
    }

    @Test
    public void countByCondition(){
        ${property} entity = new ${property}();
        int count = dao.countByCondition(entity);
        System.out.println(count);
    }
}

