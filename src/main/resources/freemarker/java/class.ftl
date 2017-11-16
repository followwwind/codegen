package ${packageName!"com.wind"};

<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

/**
 * ${classRemark!""}
 */
${scope!"public"} ${classType!"class"} ${className} {
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

<#assign t = type!"">
<#if t == "bean" && attrs??>
    <#list attrs as attr>
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name} = ${attr.name};
    }

    public ${attr.type} get${attr.name?cap_first}(){
        return this.${attr.name};
    }
    </#list>
<#elseif t == "interface" && methods??>
    <#list methods as method>
    <#assign args = []>
    <#if method.args??>
        <#assign args = method.args>
    </#if>
    /**
     * ${method.remark!""}
     */
    ${dealNull(method.scope)}${method.type} ${method.name}(${join(args)});

    </#list>
</#if>
}

<#function join args>
<#-- 声明局部变量 -->
    <#local str = "">
    <#if args??>
        <#list args as arg>
            <#if arg_index == (args?size - 1)>
                <#local str += (arg.type + " " + arg.name)>
            <#else>
                <#local str += (arg.type + " " + arg.name + ", ")>
            </#if>
        </#list>
    </#if>
    <#return str>
</#function>

<#function dealNull str>
    <#local s = "">
    <#if str??>
        <#local s = "">
    <#else>
        <#local s += str + " ">
    </#if>
    <#return s>
</#function>