package ${packageName!"com.wind"};

<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

/**
 * ${remark!""}
 * @author wind
 */
public class ${name}{

<#if fields??>
<#list fields as field>
  <#if field.remark != "" && field.remark??>
    /**
     * ${field.remark}
     */
  </#if>
    private ${field.type} ${field.name};
</#list>
</#if>

<#if fields??>
    <#list fields as field>
    public void set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
    }

    public ${field.type} get${field.name?cap_first}(){
        return this.${field.name};
    }
    </#list>
</#if>

}
