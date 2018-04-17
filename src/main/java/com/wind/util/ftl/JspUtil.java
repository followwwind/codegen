package com.wind.util.ftl;

import com.wind.config.FtlConst;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.config.Const;
import com.wind.util.JsonUtil;
import com.wind.util.StringUtil;

/**
 * jsp模板生成工具类
 * @author wind
 *
 */
public class JspUtil {
	
	/**
     * 生成通用form表单操作类
     */
    public static void genBaseForm(){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_JSP));
        freeMarker.setData("jsp/form.ftl",  "form.jsp");
        FtlUtil.genCode(freeMarker);
    }
    
    /**
     * 生成实体类对应后台管理jsp
     * @param table
     */
    public static void genTable(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_JSP));
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        freeMarker.setData("jsp/table.ftl", StringUtil.getCamelCase(property, false) + ".jsp");
        FtlUtil.genCode(freeMarker);
    }
    
    /**
     * 生成表单jsp
     * @param table
     */
    public static void genForm(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_JSP));
        freeMarker.setMap(JsonUtil.beanToMap(table, true));
        String property = table.getProperty();
        freeMarker.setData("jsp/form.ftl", "add" + property + ".jsp");
        FtlUtil.genCode(freeMarker);
    }
}
