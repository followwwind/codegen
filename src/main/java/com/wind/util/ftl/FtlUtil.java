package com.wind.util.ftl;

import com.wind.entity.clazz.ClassInfo;
import com.wind.entity.clazz.ClassType;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.ClassUtil;
import com.wind.util.Const;
import com.wind.util.JsonUtil;
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
                map.put("JDK_VERSION", Const.JDK_VERSION);
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
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_ENTITY);
        String className = table.getProperty();
        ClassInfo extend = new ClassInfo(className + "Extend", ClassType.CLASS, Const.ABSTRACT);
        extend.setPackageName("com.wind.entity.extend");
        freeMarker.setData("class.ftl", table.getProperty() + "Extend.java");
        freeMarker.setMap(JsonUtil.beanToMap(extend, true));
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_ENTITY + Const.FTL_EXTEND);
        genCode(freeMarker);
        
        freeMarker.setData("class.ftl", table.getProperty() + ".java");
        ClassInfo classInfo = ClassUtil.getBean(table);
        classInfo.setExtend(extend);
        classInfo.setPackageName("com.wind.entity");
        classInfo.initImports();
        freeMarker.setFileDir(Const.FTL_DIR + Const.FTL_ENTITY);
        freeMarker.setMap(JsonUtil.beanToMap(classInfo, true));
        genCode(freeMarker);
    }
    

    /**
     * 生成分页实体类
     */
    public static void genPage(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_ENTITY + Const.FTL_BASE);
        freeMarker.setData("page.ftl", "Page.java");
        FtlUtil.genCode(freeMarker);
    }

    /**
     * 生成通用顶层service
     */
    public static void genBaseService(){
        FreeMarker freeMarker = new FreeMarker(Const.FTL_JAVA, Const.FTL_DIR + Const.FTL_SERVICE + Const.FTL_BASE);
        freeMarker.setData("baseService.ftl", "BaseService.java");
        FtlUtil.genCode(freeMarker);
        freeMarker.setData("baseServiceImpl.ftl", "BaseServiceImpl.java");
        FtlUtil.genCode(freeMarker);
    }
}
