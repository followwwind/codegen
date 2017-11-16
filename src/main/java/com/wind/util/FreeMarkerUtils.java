package com.wind.util;

import com.wind.entity.Const;
import com.wind.entity.freemarker.FreeMarker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;


/**
 * freeMarker工具生成类
 * @author wind
 */
public class FreeMarkerUtils {

    /**
     * 生成文件
     * @param freeMarker
     */
    public static void genCode(FreeMarker freeMarker){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File(freeMarker.getCfgDir()));
            cfg.setDefaultEncoding(Const.UTF8);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template temp = cfg.getTemplate(freeMarker.getCfgName());
            File dir = new File(freeMarker.getFileDir());
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, freeMarker.getFileName()));
            Writer out = new OutputStreamWriter(fos);
            temp.process(freeMarker.getMap(), out);
            fos.flush();
            out.close();
            System.out.println("gen code success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
