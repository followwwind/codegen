package com.wind.config;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * mysql字段类型对应java bean对象
 * @author wind
 *
 */
public enum SqlConst {
	
	/**
	 * 数值类型
	 */
	TINYINT("TINYINT", "java.lang.Integer"),
	SMALLINT("SMALLINT", "java.lang.Integer"),
	MEDIUMINT("MEDIUMINT", "java.lang.Integer"),
	INT("INT", "java.lang.Integer"),
	BIGINT("BIGINT", "java.lang.Long"),
	FLOAT("FLOAT", "java.lang.Float"),
	DOUBLE("DOUBLE", "java.lang.Double"),
	DECIMAL("DECIMAL", "java.math.BigDecimal"),
	
	/**
	 * 字符串类型
	 */
	CHAR("CHAR", "java.lang.String"),
	VARCHAR("VARCHAR", "java.lang.String"),
	TINYBLOB("TINYBLOB", "java.lang.Integer"),
	TINYTEXT("TINYTEXT", "java.lang.String"),
	BLOB("BLOB", "java.lang.Integer"),
	TEXT("TEXT", "java.lang.String"),
	MEDIUMBLOB("MEDIUMBLOB", "java.lang.Integer"),
	MEDIUMTEXT("MEDIUMTEXT", "java.lang.String"),
	LONGBLOB("LONGBLOB", "java.lang.Integer"),
	LONGTEXT("LONGTEXT", "java.lang.String"),

	/**
	 * bool类型
	 */
	BIT("BIT", "java.lang.Boolean"),
	
	/**
	 * 时间类型
	 */
	DATE("DATE", "java.util.Date"),
	TIME("TIME", "java.util.Date"),
	YEAR("YEAR", "java.util.Date"),
	DATETIME("DATETIME", "java.util.Date"),
	TIMESTAMP("TIMESTAMP", "java.util.Date")
	;

	/**
	 * mysql字段类型名称
	 */
	private  String name;
	/**
	 * 对应java类型
	 */
	private String type;
	

	SqlConst(String name, String type) {
		this.name = name;
		this.type = type;
	}	

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	/**
	 * 表字段类型转换成java类型
	 * @param columnType
	 * @return
	 * @throws Exception 
	 */
	public static String getFieldType(String columnType){
		Optional<SqlConst> opt = Stream.of(SqlConst.values()).
				filter(val -> {
					String name = val.name;
					return columnType.equals(name) || columnType.equals(name + " UNSIGNED");
				}).findFirst();

		if(opt.isPresent()) {
			String type = opt.get().getType();
			return type;
		}
		
		return null;
	}
}
