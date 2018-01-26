package com.wind.entity.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶层条件用例
 * @author wind
 *
 */
public abstract class Example {
	/**
	 * 排序从句
	 */
	private String orderClause;
	/**
	 * 分组从句
	 */
	private String groupClause;
	/**
	 * 分页从句
	 */
	private String limit;
	
	/**
	 * where 条件从句
	 */
	private List<Condition> conditions;
	
	public Example() {
		this.conditions = new ArrayList<Condition>();
	}
	
	/**
	 * 清空条件
	 */
	public void clear(){
		
		if(conditions != null){
			conditions.clear();
		}
		
		orderClause = null;
		groupClause = null;
		limit = null;
	}
	
	/**
	 * 添加or条件从句
	 */
	public void addCondition(Condition c){
		if(conditions == null){
			conditions = new ArrayList<>();
		}
		conditions.add(c);
	}
	

	public String getOrderClause() {
		return orderClause;
	}

	public void setOrderClause(String orderClause) {
		this.orderClause = orderClause;
	}

	public String getGroupClause() {
		return groupClause;
	}

	public void setGroupClause(String groupClause) {
		this.groupClause = groupClause;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
}
