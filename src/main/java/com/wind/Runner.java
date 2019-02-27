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
 * @Description: TODO
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
        if(args.length == 1){
            Properties config = PropUtil.getProp(args[0]);
            init(config);
        }

        FtlUtil.clear();
        String pattern = EnvUtil.get(EnvType.TABLE_PATTERN);
        String head = EnvUtil.get(EnvType.REPLACE_HEAD);
        List<Table> tables = new ArrayList<>(DbUtil.getTable(pattern));
        tables.forEach(table -> {
            String property = table.getProperty();
            table.setProperty(property.replaceAll(StringUtil.getCamelCase(head, true), ""));
            MybatisUtil.genController(table, false);
            FtlUtil.genEntity(table, false);
            MybatisUtil.genMapper(table, true);
            MybatisUtil.genService(table, true);
        });
    }
}
