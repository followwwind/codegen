package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.JspUtil;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wind
 */
public class JspTest {

    private List<Table> tables = new ArrayList<>();

    @Before
    public void init(){
        //tables.addAll(DbUtil.getTables("test"));
        tables.add(DbUtil.getTable("test", "demo"));
    }


    @Test
    public void genTables(){
        tables.forEach(JspUtil::genTable);
    }

    
    @Test
    public void genForms(){
        tables.forEach(JspUtil::genForm);
    }

}
