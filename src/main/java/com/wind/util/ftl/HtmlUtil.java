package com.wind.util.ftl;

import com.wind.config.FtlConst;
import com.wind.config.PathConst;
import com.wind.entity.ftl.FreeMarker;
import com.wind.config.Const;
import com.wind.util.StringUtil;

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
        FreeMarker freeMarker = new FreeMarker(PathConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, PathConst.FTL_DIR_PATH, FtlConst.FTL_HTML));
        freeMarker.setData("html/form.ftl",  "form.html");
        FtlUtil.genCode(freeMarker);
    }
}
