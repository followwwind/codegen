package com.wind.util.ftl;

import com.wind.config.FtlConst;
import com.wind.config.JavaConst;
import com.wind.entity.clazz.ClassInfo;
import com.wind.entity.clazz.ClassType;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.ClassUtil;
import com.wind.config.Const;
import com.wind.util.JsonUtil;
import com.wind.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * freeMarker工具生成类
 * @author wind
 */
public class FtlUtil {

    /**
     * 生成文件
     * @param freeMarker
     */
    public static void genCode(FreeMarker freeMarker){
        try {
            File dir = new File(freeMarker.getFileDir());
            boolean sign = true;
            if(!dir.exists()){
                sign = dir.mkdirs();
            }

            if(sign){
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
                cfg.setDirectoryForTemplateLoading(new File(freeMarker.getCfgDir()));
                cfg.setDefaultEncoding(Const.UTF8);
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                Template temp = cfg.getTemplate(freeMarker.getCfgName());

                OutputStream fos = new FileOutputStream( new File(dir, freeMarker.getFileName()));
                Writer out = new OutputStreamWriter(fos);
                Map<String, Object> map = freeMarker.getMap();
                if(map == null){
                    map = new HashMap<>(Const.MAP_SIZE);
                }
                map.put("JDK_VERSION", FtlConst.JDK_VERSION);
                temp.process(map, out);
                fos.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成表关联对应的实体类以及拓展类
     * @param table
     */
    public static void genEntity(Table table){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(FtlConst.FTL_DIR + Const.FILE_SEPARATOR + FtlConst.FTL_ENTITY);
        String className = table.getProperty();
        String extendClassName = className + StringUtil.getFirst(FtlConst.FTL_EXTEND, true);
        ClassInfo extend = new ClassInfo(extendClassName, ClassType.CLASS, JavaConst.ABSTRACT + Const.SPACE_STR + JavaConst.CLASS);
        extend.setPackageName(StringUtil.joinStr(Const.POINT_STR, FtlConst.FTL_PACKAGR, FtlConst.FTL_ENTITY, FtlConst.FTL_EXTEND));
        freeMarker.setData("class.ftl", StringUtil.joinStr(Const.POINT_STR, extendClassName, JavaConst.JAVA));
        freeMarker.setMap(JsonUtil.beanToMap(extend, true));
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_ENTITY, FtlConst.FTL_EXTEND));
        genCode(freeMarker);
        
        freeMarker.setData("class.ftl", StringUtil.joinStr(Const.POINT_STR, className, JavaConst.JAVA));
        ClassInfo classInfo = ClassUtil.getBean(table);
        classInfo.setExtend(extend);
        classInfo.setPackageName(StringUtil.joinStr(Const.POINT_STR, FtlConst.FTL_PACKAGR, FtlConst.FTL_ENTITY));
        classInfo.initImports();
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_ENTITY));
        freeMarker.setMap(JsonUtil.beanToMap(classInfo, true));
        genCode(freeMarker);
    }
    

    /**
     * 生成分页实体类
     */
    public static void genPage(){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_ENTITY, FtlConst.FTL_BASE));
        freeMarker.setData("page.ftl", "Page.java");
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成通用顶层service
     */
    public static void genBaseService(){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        freeMarker.setFileDir(StringUtil.joinStr(Const.FILE_SEPARATOR, FtlConst.FTL_DIR, FtlConst.FTL_SERVICE, FtlConst.FTL_BASE));
        freeMarker.setData("baseService.ftl", "BaseService.java");
        FtlUtil.genCode(freeMarker);
        freeMarker.setData("baseServiceImpl.ftl", "BaseServiceImpl.java");
        FtlUtil.genCode(freeMarker);
    }
}
