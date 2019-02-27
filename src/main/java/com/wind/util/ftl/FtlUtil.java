package com.wind.util.ftl;

import com.wind.config.*;
import com.wind.entity.clazz.ClassInfo;
import com.wind.entity.clazz.ClassType;
import com.wind.entity.db.Table;
import com.wind.entity.ftl.FreeMarker;
import com.wind.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Stream;


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
    	delDir(new File(EnvUtil.get(EnvType.ROOT_PATH)));
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

    /**
     * 生成表关联对应的实体类以及拓展类
     * @param flag true表示生成拓展类
     * @param table
     */
    public static void genEntity(Table table, boolean flag){
        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
        String className = table.getProperty();
//        String extendClassName = className + StringUtil.getFirst(FtlConst.FTL_EXTEND, true);
//        ClassInfo extend = null;
//        if(flag){
//        	extend = new ClassInfo(extendClassName, ClassType.CLASS, JavaConst.ABSTRACT + Const.SPACE_STR + JavaConst.CLASS);
//            extend.setPackageName(PackageConst.FTL_ENTITY_EXTEND_PACKAGE);
//            freeMarker.setData("class.ftl", StringUtil.joinStr(Const.POINT_STR, extendClassName, JavaConst.JAVA));
//            freeMarker.setMap(ReflectUtil.beanToMap(table, true));
//            freeMarker.setFileDir(PathConst.FTL_ENTITY_EXTEND_PATH);
//            genCode(freeMarker);
//        }
        freeMarker.setData("class.ftl", StringUtil.joinStr(Const.POINT_STR, className, JavaConst.JAVA));
        ClassInfo classInfo = ClassUtil.getBean(table);
//        classInfo.setExtend(extend);
        classInfo.setPackageName(EnvUtil.getPackage(EnvType.ENTITY));
        classInfo.initImports();
        freeMarker.setFileDir(EnvUtil.getPath(EnvType.ENTITY));
        freeMarker.setMap(ReflectUtil.beanToMap(classInfo, true));
        genCode(freeMarker);
    }


    /**
     * 生成通用顶层service
     */
    public static void genBaseService(){
//        FreeMarker freeMarker = new FreeMarker(FtlConst.FTL_JAVA);
//        freeMarker.setFileDir(PathConst.FTL_SERVICE_BASE_PATH);
//        freeMarker.setData("baseService.ftl", "BaseService.java");
//        Map<String, Object> map = new HashMap<>(16);
//        map.put(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_SERVICE_BASE_PACKAGE);
//        List<String> imports = new ArrayList<>();
//        imports.add(StringUtil.joinStr(Const.POINT_STR, PackageConst.FTL_COMMON_PERSISTENCE_PACKAGE, "Page"));
//        map.put(FtlConst.FTL_IMPORT, imports);
//        freeMarker.addMap(map);
//        FtlUtil.genCode(freeMarker);
//        freeMarker.addMap(FtlConst.FTL_PACKAGE_NAME, PackageConst.FTL_SERVICE_BASE_PACKAGE);
//        freeMarker.setData("baseServiceImpl.ftl", "BaseServiceImpl.java");
//        FtlUtil.genCode(freeMarker);
    }
}
