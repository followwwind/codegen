package com.wind.entity.base;

/**
 * where条件拼接
 * @author wind
 *
 */
public class Attribute {
	
	private String key;
	
	/**
	 * 条件值
	 */
	private Object value;
	
	/**
	 * 类型  
	 */
	private String type;
	
	

	public Attribute(String key, Object value, AttrType type) {
		super();
		this.key = key;
		this.value = value;
		this.type = type.getName();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
