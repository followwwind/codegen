package com.wind.config;

/**
 * mysql字段类型对应java bean对象
 * @author wind
 *
 */
public enum MysqlConst {
	
	/**
	 * 数值类型
	 */
	TINYINT("TINYINT", "Integer"),
	SMALLINT("SMALLINT", "Integer"),
	MEDIUMINT("MEDIUMINT", "Integer"),
	INT("INT", "Integer"),
	BIGINT("BIGINT", "java.math.BigInteger"),
	FLOAT("FLOAT", "Float"),
	DOUBLE("DOUBLE", "Double"),
	DECIMAL("DECIMAL", "java.math.BigDecimal"),
	
	/**
	 * 字符串类型
	 */
	CHAR("CHAR", "String"),
	VARCHAR("VARCHAR", "String"),
	TINYBLOB("TINYBLOB", "Integer"),
	TINYTEXT("TINYTEXT", "String"),
	BLOB("SMALLINT", "Integer"),
	TEXT("TEXT", "String"),
	MEDIUMBLOB("SMALLINT", "Integer"),
	MEDIUMTEXT("MEDIUMTEXT", "String"),
	LONGBLOB("SMALLINT", "Integer"),
	LONGTEXT("LONGTEXT", "String"),

	/**
	 * bool类型
	 */
	BIT("BIT", "Boolean"),
	
	/**
	 * 时间类型
	 */
	DATE("DATE", "java.util.Date"),
	TIME("TIME", "java.util.Date"),
	YEAR("YEAR", "java.util.Date"),
	DATATIME("DATATIME", "java.util.Date"),
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

	MysqlConst(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * 表字段类型转换成java类型
	 * @param columnType
	 * @return
	 */
	public static String getFieldType(String columnType){
		String result = "";
		for(MysqlConst mysqlConst : MysqlConst.values()){
			String name = mysqlConst.name;
			if(name.equals(columnType)){
				result = mysqlConst.type;
				break;
			}
		}
		return result;
	}
}
