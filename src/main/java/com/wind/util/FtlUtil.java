package com.wind.util;

import com.wind.config.Const;
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

    /**
     * 生成文件
     * @param fileName
     * @param target
     * @param ftlName
     * @param map
     */
    public static void genCode(String fileName, String target, String ftlPath, String ftlName, Map<String, Object> map){
        try {
            File dir = new File(target);
            boolean sign = true;
            if(!dir.exists()){
                sign = dir.mkdirs();
            }

            if(sign){
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
                cfg.setDirectoryForTemplateLoading(new File(ftlPath));
                cfg.setDefaultEncoding(Const.UTF8);
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                Template temp = cfg.getTemplate(ftlName);
                OutputStream fos = new FileOutputStream( new File(dir, fileName));
                Writer out = new OutputStreamWriter(fos);
                if(map == null){
                    map = new HashMap<>(Const.MAP_SIZE);
                }
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
     * 清空目标生成目录
     * @param dir
     */
    public static void clear(File dir) {
    	if (dir != null) {
            if(dir.isDirectory()){
                File[] files = dir.listFiles();
                if(files != null){
                    Stream.of(files).forEach(FtlUtil::clear);
                }
            }
            dir.delete();
        }
    }
}
