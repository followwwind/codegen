package com.wind;

import com.wind.config.EnvType;
import com.wind.entity.db.Table;
import com.wind.util.DbUtil;
import com.wind.util.EnvUtil;
import com.wind.util.PropUtil;
import com.wind.util.StringUtil;
import com.wind.util.ftl.FtlUtil;
import com.wind.util.ftl.MybatisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Title: Runner
 * @Package com.wind
 * @Description: 程序入口
 * @author wind
 * @date 2019/2/27 15:06
 * @version V1.0
 */
public class Runner {

    private static void init(Properties config){
        if(config != null){
            config.forEach((key, value) -> EnvUtil.set(String.valueOf(key), String.valueOf(value)));
        }
    }

    public static void main(String[] args) {
        Properties config = args.length == 1 ? PropUtil.getProp(args[0]) :
                PropUtil.getProp(DbUtil.class.getResourceAsStream("/config.properties"));
        init(config);

        FtlUtil.clear();
        String pattern = EnvUtil.getValOrDefault(EnvType.TABLE_PATTERN);
        String head = EnvUtil.getValOrDefault(EnvType.REPLACE_HEAD);
        List<Table> tables = new ArrayList<>(DbUtil.getTable(pattern));
        tables.forEach(table -> {
            String property = table.getProperty();
            table.setProperty(property.replaceAll(StringUtil.getCamelCase(head, true), ""));
            MybatisUtil.genController(table, false);
            FtlUtil.genEntity(table);
            MybatisUtil.genMapper(table, true);
            MybatisUtil.genService(table, true);
        });
    }
}
