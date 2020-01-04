package com.wind.util.ftl;

import com.wind.config.FtlConst;
import com.wind.enums.PathType;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.EnvUtil;
import com.wind.util.ReflectUtil;
import com.wind.util.StringUtil;

/**
 * @Title: AngularJsUtil
 * @Package com.wind.util.ftl
 * @Description: angular模板生成工具类
 * @author wind
 * @date 2020/1/4 17:08
 * @version V1.0
 */
public class AngularJsUtil {

    private AngularJsUtil(){

    }

    public static void genTable(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.ANGULAR_TABLE));
        String property = table.getProperty();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        freeMarker.setData("angular/table_html.ftl", StringUtil.getCamelCase(property, false) + ".html");
        FtlUtil.genCode(freeMarker);
    }

    public static void genJs(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(EnvUtil.getPath(PathType.ANGULAR_JS));
        String property = table.getProperty();
        freeMarker.setMap(ReflectUtil.beanToMap(table, true));
        freeMarker.setData("angular/table_js.ftl", StringUtil.getCamelCase(property, false) + ".js");
        FtlUtil.genCode(freeMarker);
    }

}
