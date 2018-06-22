package com.wind.config;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * mysql字段类型对应java bean对象
 * @author wind
 *
 */
public enum MysqlConst {
	
	/**
	 * 数值类型
	 */
	TINYINT("TINYINT", "Integer", ""),
	SMALLINT("SMALLINT", "Integer", ""),
	MEDIUMINT("MEDIUMINT", "Integer", ""),
	INT("INT", "Integer", ""),
	BIGINT("BIGINT", "BigInteger", "java.math.BigInteger"),
	FLOAT("FLOAT", "Float", ""),
	DOUBLE("DOUBLE", "Double", ""),
	DECIMAL("DECIMAL", "BigDecimal", "java.math.BigDecimal"),
	
	/**
	 * 字符串类型
	 */
	CHAR("CHAR", "String", ""),
	VARCHAR("VARCHAR", "String", ""),
	TINYBLOB("TINYBLOB", "Integer", ""),
	TINYTEXT("TINYTEXT", "String", ""),
	BLOB("SMALLINT", "Integer", ""),
	TEXT("TEXT", "String", ""),
	MEDIUMBLOB("SMALLINT", "Integer", ""),
	MEDIUMTEXT("MEDIUMTEXT", "String", ""),
	LONGBLOB("SMALLINT", "Integer", ""),
	LONGTEXT("LONGTEXT", "String", ""),

	/**
	 * bool类型
	 */
	BIT("BIT", "Boolean", ""),
	
	/**
	 * 时间类型
	 */
	DATE("DATE", "Date", "java.util.Date"),
	TIME("TIME", "Date", "java.util.Date"),
	YEAR("YEAR", "Date", "java.util.Date"),
	DATETIME("DATETIME", "Date", "java.util.Date"),
	TIMESTAMP("TIMESTAMP", "Date", "java.util.Date")
	;

	/**
	 * mysql字段类型名称
	 */
	private  String name;
	/**
	 * 对应java类型
	 */
	private String type;
	
	/**
	 * 类名全路径
	 */
	private String className;

	MysqlConst(String name, String type, String className) {
		this.name = name;
		this.type = type;
		this.className = className;
	}	

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getClassName() {
		return className;
	}



	/**
	 * 表字段类型转换成java类型
	 * @param columnType
	 * @return
	 */
	public static String getFieldType(String columnType){
		Optional<MysqlConst> opt = Stream.of(MysqlConst.values()).
				filter(val -> columnType.contains(val.name)).findFirst();
		if(opt.isPresent()) {
			return opt.get().getType();
		}
		
		new Throwable("表字段类型转换成java类型，找不到类型");
		return "";
	}
}
