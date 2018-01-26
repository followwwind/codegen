package com.wind.util.ftl;

import com.wind.entity.ftl.FreeMarker;
import com.wind.util.Const;

/**
 * html模板生成工具类
 * @author wind
 *
 */
public class HtmlUtil {
	
	/**
     * 生成通用form表单操作类
     */
    public static void genBaseForm(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_HTML);
        freeMarker.setData("html/form.ftl",  "form.html");
        FtlUtil.genCode(freeMarker);
    }
}
