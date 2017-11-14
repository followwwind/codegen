package ${packageName};

<#list imports as import>
import ${import};
</#list>

<#if classRemark != "">
/**
 * ${classRemark}
 */
</#if>
${scope} ${classType} ${className} {
<#list attrs as attr>
  <#if attr.remark != "">
  /**
   * ${attr.remark}
   */
  </#if>
  ${attr.scope} ${attr.type} ${attr.name};
</#list>

<#list attrs as attr>
  public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
    this.${attr.name} = ${attr.name};
  }

  public ${attr.type} get${attr.name?cap_first}(){
    return this.${attr.name};
  }
</#list>
}