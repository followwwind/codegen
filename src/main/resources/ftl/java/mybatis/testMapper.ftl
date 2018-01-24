package com.wind.dao;

import java.util.List;
import com.wind.dao.${property}Mapper;
import com.wind.entity.${property};
import com.wind.entity.example.${property}Example;
import com.wind.entity.base.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * ${remarks!""} dao测试
 * @author wind
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class ${property}MapperTest{

    @Autowired
    ${property}Mapper mapper;

    @Test
    public void insert() {
        ${property} entity = new ${property}();
        for(int i = 0; i < 10; i++){
        	mapper.insert(entity);
        }
    }

    @Test
    public void deleteByCondition() {
        ${property} entity = new ${property}();
        int i = mapper.deleteByCondition(entity);
        System.out.println(i);
    }

    @Test
    public void findEntity() {
        ${property} entity = new ${property}();
        List<${property}> entitys = mapper.findEntitys(entity);
        System.out.println(entitys.size());
    }

    @Test
    public void findByCondition() {
        ${property}Example example = new ${property}Example();
        Page page = new Page();
        example.setLimit(page.getStartRow() + "," + page.getLineNumber());
        List<${property}> entitys = mapper.findByCondition(example);
        System.out.println(entitys.size());
    }

    @Test
    public void updateByCondition() {
        ${property} entity = new ${property}();
        int i = mapper.updateByCondition(entity);
        System.out.println(i);
    }

    @Test
    public void countByCondition(){
        ${property}Example example = new ${property}Example();
        int count = mapper.countByCondition(example);
        System.out.println(count);
    }
}

