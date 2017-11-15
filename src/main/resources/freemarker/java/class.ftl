<#if packageName??>
package ${packageName};
</#if>
<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

/**
 * ${classRemark!""}
 */
${scope} ${classType} ${className} {
<#if attrs??>
<#list attrs as attr>
  <#if attr.remark != "">
  /**
   * ${attr.remark}
   */
  </#if>
  ${attr.scope} ${attr.type} ${attr.name};
</#list>
</#if>

<#if type == "bean" && attrs??>
    <#list attrs as attr>
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }

    public ${attr.type} get${attr.name?cap_first}(){
        return this.${attr.name};
    }
    </#list>
<#elseif type == "interface" && methods??>
    <#list methods as method>
    <#assign args = "">
    <#if method.args??>
        <#assign args = method.args>
    </#if>
    /**
     * ${method.remark!""}
     */
    ${method.type} ${method.name}(${join(args)});
    </#list>
</#if>
}

<#function join args>
<#-- 声明局部变量 -->
    <#local str = "">
    <#if args != "">
        <#list args as arg>
            <#local str += (arg.type + " " + arg.name + ", ")>
        </#list>
        <#if str.endsWith(", ")>
            <#local str = str.substring(0, str.length() - 2)>
        </#if>
    </#if>
    <#return str>
</#function>