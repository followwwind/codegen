package com.wind.ftl;

import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.ftl.JspUtil;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * @author wind
 */
public class JspTest {

    List<Table> tables = DbUtil.getTables("test");

    Table table = DbUtil.getTable("test", "user_info");

    @Before
    public void init(){

    }


    @Test
    public void genTables(){
        tables.forEach(JspUtil::genTable);
    }


    @Test
    public void genTable(){
        JspUtil.genTable(table);
    }
    
    @Test
    public void genForms(){
        tables.forEach(JspUtil::genForm);
    }


    @Test
    public void genForm(){
        JspUtil.genForm(table);
    }
}
