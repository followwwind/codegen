package ${packageName!"com.wind.entity.example"};

import java.util.Arrays;
import java.util.List;
import java.util.Date;
<#if imports??>
	<#list imports as import>
import ${import};
	</#list>
</#if>

/**
 * ${remarks!""} where条件从句Exmaple
 * @author wind
 *
 */
public class ${property}Example extends Example{
	
	public static Builder newBuilder(){
		return new Builder();
	}
	
	public static class Builder extends BaseBuilder{
		
		public Builder() {
			super();
		}
		
		<#list columns as column>
		<#assign columnName = column.columnName>
        <#assign type = column.type>
        <#assign colProperty = column.property>
	    public Builder and${colProperty?cap_first}IsNull(){
			addAttr(new Attribute("${columnName} is null", null, AttrType.NO));
			return this;
		}
		
		public Builder and${colProperty?cap_first}IsNotNull(){
			addAttr(new Attribute("${columnName} is not null", null, AttrType.NO));
			return this;
		}
		
		public Builder and${colProperty?cap_first}EqualTo(${type} value) {
	        addAttr(new Attribute("${columnName} =", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}NotEqualTo(${type} value) {
	        addAttr(new Attribute("${columnName} <>", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Gt(${type} value) {
	        addAttr(new Attribute("${columnName} >", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Gte(${type} value) {
	        addAttr(new Attribute("${columnName} >=", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Lt(String value) {
	        addAttr(new Attribute("${columnName} <", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Lte(${type} value) {
	        addAttr(new Attribute("${columnName} <=", value, AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Like(${type} value) {
	        addAttr(new Attribute("${columnName} like", "%" + value + "%", AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}NotLike(${type} value) {
	        addAttr(new Attribute("${columnName} not like", "%" + value + "%", AttrType.SINGLE));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}In(List<${type}> values) {
	        addAttr(new Attribute("${columnName} in", values, AttrType.IN));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}NotIn(List<${type}> values) {
	        addAttr(new Attribute("${columnName} not in", values, AttrType.IN));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}Between(${type} value1, ${type} value2) {
	        addAttr(new Attribute("${columnName} between", Arrays.asList(value1, value2), AttrType.BETWEEN));
	        return this;
	    }

	    public Builder and${colProperty?cap_first}NotBetween(${type} value1, ${type} value2) {
	        addAttr(new Attribute("${columnName} not between", Arrays.asList(value1, value2), AttrType.BETWEEN));
	        return this;
	    }     
	    </#list>
	}
}
