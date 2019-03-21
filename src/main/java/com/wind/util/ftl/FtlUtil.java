package com.wind.util.ftl;

import com.wind.config.*;
import com.wind.entity.clazz.ClassInfo;
import com.wind.entity.clazz.ClassType;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.ClassUtil;
import com.wind.util.EnvUtil;
import com.wind.util.ReflectUtil;
import com.wind.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


/**
 * freeMarker工具生成类
 * @author wind
 */
public class FtlUtil {

    private FtlUtil(){

    }

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
//                String path = FtlUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//                cfg.setDirectoryForTemplateLoading(new File(path + "ftl/java"));
                cfg.setClassForTemplateLoading(FtlUtil.class, "/ftl/java");
                cfg.setDefaultEncoding(Const.UTF8);
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                Template temp = cfg.getTemplate(freeMarker.getCfgName());

                OutputStream fos = new FileOutputStream( new File(dir, freeMarker.getFileName()));
                Writer out = new OutputStreamWriter(fos, Const.UTF8);
                Map<String, Object> map = freeMarker.getMap();
                if(map == null){
                    map = new HashMap<>(Const.MAP_SIZE);
                }
                map.put("JDK_VERSION", EnvType.JDK_VERSION.getValue());
                temp.process(map, out);
                fos.flush();
                out.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 清空工作目录
     */
    public static void clear() {
    	delDir(new File(EnvUtil.getValOrDefault(EnvType.TARGET)));
    }
    
    /**
     * 清空目标生成目录
     */
    public static void delDir(File dir) {
    	if (dir != null) {
            if(dir.isDirectory()){
                File[] files = dir.listFiles();
                Stream.of(files != null ? files : new File[0]).forEach(FtlUtil::delDir);
            }else{
                dir.delete();
            }
        }
    }

    public static void genEntity(Table table){
        genPO(table, true);
        genVO(table, true);
        genQ(table, true);
        genSearch(table, true);
    }

    /**
     * genPO
     * @param table
     * @param flag
     */
    public static void genPO(Table table, boolean flag){
        ClassInfo extend = null;
        if(flag){
            extend = new ClassInfo(EnvType.BASE_PO.getKey());
            extend.addImport(EnvType.BASE_PO.getValue());
            extend.setPackageName(EnvType.PERSISTENCE_PO.getValue());
        }
        genEntity(table, extend, PackageType.ENTITY_PO, PathType.ENTITY_PO, null);
    }

    /**
     * genQ
     * @param table
     * @param flag
     */
    public static void genQ(Table table, boolean flag){
        ClassInfo extend = null;
        if(flag){
            extend = new ClassInfo(EnvType.BASE_QUERY.getKey());
            extend.addImport(EnvType.BASE_QUERY.getValue());
            extend.setPackageName(EnvType.PERSISTENCE_PO.getValue());
        }
        String className = table.getProperty() + FtlConst.Q;
        genEntity(table, extend, PackageType.ENTITY_QUERY, PathType.ENTITY_QUERY, className);
    }

    /**
     * genVO
     * @param table
     * @param flag
     */
    public static void genVO(Table table, boolean flag){
        ClassInfo extend = null;
        if(flag){
            extend = new ClassInfo(EnvType.BASE_VO.getKey());
            extend.addImport(EnvType.BASE_PO.getValue());
            extend.setPackageName(EnvType.PERSISTENCE_PO.getValue());
        }
        String className = table.getProperty() + FtlConst.VO;
        genEntity(table, extend, PackageType.ENTITY_VO, PathType.ENTITY_VO, className);
    }

    /**
     * genSearch
     * @param table
     * @param flag
     */
    public static void genSearch(Table table, boolean flag){
        ClassInfo extend = null;
        if(flag){
            extend = new ClassInfo(EnvType.PAGE_QUERY.getKey());
            extend.addImport(EnvType.PAGE_QUERY.getValue());
            extend.setPackageName(EnvType.PERSISTENCE_PO.getValue());
        }
        String className = table.getProperty() + FtlConst.SEARCH;
        genEntity(table, extend, PackageType.ENTITY_QUERY, PathType.ENTITY_QUERY, className);
    }

    public static void genEntity(Table table, ClassInfo extend, PackageType packageType, PathType pathType, String className){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        className = className != null ? className : table.getProperty();
        freeMarker.setData("class.ftl", StringUtil.joinStr(Const.POINT_STR, className, JavaConst.JAVA));
        ClassInfo classInfo = ClassUtil.getBean(table);
        if(extend != null){
            classInfo.setExtend(extend);
        }
        classInfo.setPackageName(EnvUtil.getPackage(packageType));
        classInfo.initImports();
        freeMarker.setFileDir(EnvUtil.getPath(pathType));
        freeMarker.setMap(ReflectUtil.beanToMap(classInfo, true));
        genCode(freeMarker);
    }
}
