package ${packageName!"com.wind"};

<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

<#assign e = "">
<#if extend??>
    <#assign e = " extends " + extend.name>
</#if>
<#assign imp = []>
<#if impls??>
    <#assign imp = impls>
</#if>
/**
 * ${remark!"@author wind"}
 */
${scope!"public"} ${type!"class"} ${name}${e}${join(imp, "," , 1)}{
<#if fields??>
<#list fields as field>
  <#if field.remark != "" && field.remark??>
    /**
     * ${field.remark}
     */
  </#if>
    ${field.scope!"private"} ${field.type} ${field.name};

</#list>
</#if>

<#assign t = classType!"">
<#if t == "BEAN">
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

<#else>
    <#if methods??>
    <#list methods as method>
        <#assign args = []>
        <#if method.args??>
            <#assign args = method.args>
        </#if>
        <#if type == "interface">
    /**
     * ${method.remark!""}
     */
    ${method.type} ${method.name}(${join(args, "," , 0)});

        <#elseif type == "class">
    ${dealNull(method.scope)}${method.type} ${method.name}(${join(args, "," , 0)}){
        ${method.body!""}
        <#if method.type != "void">
        return ${method.result};
        </#if>
    }

        </#if>
    </#list>
    </#if>
</#if>
}

<#function join args sign type>
<#-- 声明局部变量 -->
    <#local str = "">
    <#if args??>
        <#if type == 1 && !(args?size <= 0)>
            <#local str = " implements ">
        </#if>
        <#list args as arg>
            <#if type == 0>
                <#if arg_index == (args?size - 1)>
                    <#local str += (arg.type + " " + arg.name)>
                <#else>
                    <#local str += (arg.type + " " + arg.name + sign + " ")>
                </#if>
            <#elseif type == 1>
                <#if arg_index == (args?size - 1)>
                    <#local str += arg.name>
                <#else>
                    <#local str += arg.name + sign>
                </#if>
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