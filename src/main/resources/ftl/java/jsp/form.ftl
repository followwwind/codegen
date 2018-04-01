<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.wind.entity.${property}"%>
<%@page import="com.wind.service.${property}Service"%>
<%@page import="com.wind.service.impl.${property}ServiceImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<#assign dlBracket = "${"/>
<#assign uncapProp = property?uncap_first/>
<#if primaryKeys?? && primaryKeys?size gt 0>
    <#assign key = getKey(columns, primaryKeys[0])>
    <#assign pkName = key.columnName>
    <#assign pkPro = key.property>
    <#assign type = key.type>
<#else>
    <#assign pkName = "">
    <#assign pkPro = "">
    <#assign type = "String">
</#if>
<%
	String id = request.getParameter("id");
    String flag = request.getParameter("flag");
    ${property} ${uncapProp} = new ${property}();
    ${property}Service ${uncapProp}Service = new ${property}ServiceImpl();
	
	if(id == null || "".equals(id) || !"update".equals(flag)){
		<#list columns as column>
        <#if column.columnType != "TIMESTAMP" && column.columnName != pkName>
        String ${column.property} = request.getParameter("${column.property}");
        <#if column.type == "Integer">
        ${uncapProp}.set${column.property?cap_first}(Integer.valueOf(${column.property}));
        <#elseif column.type == "String">
        ${uncapProp}.set${column.property?cap_first}(${column.property});
        </#if>
        </#if>
        </#list>
	}
	
	if(id == null || "".equals(id)){
		${uncapProp}Service.insert(${uncapProp});
		request.getRequestDispatcher("${uncapProp}.jsp").forward(request, response);
	}else{
		if("update".equals(flag)){
			${uncapProp}.set${pkPro?cap_first}(id);
			${property} e = ${uncapProp}Service.findEntity(${uncapProp});
			if(e != null){
				request.setAttribute("entity", e);
			}
		}else{
			${uncapProp}.set${pkPro?cap_first}(id);
			${uncapProp}Service.updateByCondition(${uncapProp});
			request.getRequestDispatcher("${uncapProp}.jsp").forward(request, response);
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>add or update</title>
    <style type="text/css">
        .content{
            margin: 240px auto;
        }
        table {
            width: 30%;
            border-spacing:0;
            color:green;
            margin: 10px auto;
        }
        td {
            padding-top:10px;
            padding-left: 10px;
            padding-bottom: 10px;
        }
        .black {
            color: black;
            text-align: right;
        }
    </style>
</head>
<body>
<div class="content">
    <form action="add${property}.jsp" method="post">
    	<input type="hidden" name="id" 
                value="${dlBracket}entity != null ? entity.${pkPro} : ""}" style="width: 300px;">
        <table border="1">
            <tr>
                <th colspan="3">
                    <span class="black">表单页面</span>
                </th>
            </tr>
            <#list columns as column>
            <#if column.columnType != "TIMESTAMP" && column.columnName != pkName>
            <tr>
                <td>
                    <span class="black">${column.property}:</span>
                </td>
                <td colspan="2">
                    <input type="text" name="${column.property}" 
                    value="${dlBracket}entity != null ? entity.${column.property} : ""}" style="width: 300px;">
                </td>
            </tr>
            </#if>
            </#list>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="提交数据">
                    <input type="reset" value="清除数据">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
<#function getKey columns primary>
    <#local b = {}>
    <#list columns as column>
        <#if primary.colName == column.columnName>
            <#local b = column>
        </#if>
    </#list>
    <#return b>
</#function>