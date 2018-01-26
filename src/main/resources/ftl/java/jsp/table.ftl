<%@page language="java" contentType="text/html;charset=uft-8" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.wind.entity.${property}"%>
<%@page import="com.wind.service.${property}Service"%>
<%@page import="com.wind.service.impl${property}ServiceImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<#assign dlBracket = "${"/>
<#if primaryKeys?? && primaryKeys?size gt 0>
    <#assign key = getKey(columns, primaryKeys[0])>
    <#assign type = key.type>
    <#assign keyProp = key.property>
<#else>
    <#assign type = "String">
</#if>
<%
	String id = request.getParameter("id");
	${property} entity = new ${property}();
	${property}Service ${property?uncap_first}Service = new ${property}ServiceImpl();
	if(id != null && "".equals(id)){
		entity.set${keyProp?cap_first!""}(id);
		${property?uncap_first}Service.deleteByCondition(entity);
		response.sendRedirect("${property?uncap_first}.jsp");
	}else{
		List<${property}> entitys = ${property?uncap_first}Service.findByCondition(entity);
		request.setAttribute("entitys", entitys);
	}
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>${property}</title>
	<!-- <script type="text/javascript" src="js/jquery.js"></script> -->
</head>
<body>
    <table class="">
    	<thead>
			<tr>
				<th><input name="" type="checkbox" value="" checked="checked"/></th>
				<#list columns as column>
				<th>${column.property}</th>
				</#list>
				<th>操作</th>
			</tr>
        </thead>
        <tbody>
        
        
        <c:forEach items="${dlBracket}entitys}"  var="entity">
        <tr>
			<td><input name="" type="checkbox" value="" /></td>
			<#list columns as column>
			<#if column.columnType == "TIMESTAMP">
			<td><fmt:formatDate value="${dlBracket}entity.${column.property}}" pattern="yyyy-MM-dd"/></td>
			<#else>
			<td>${dlBracket}entity.${column.property}}</td>
			</#if>
			</#list>
			<td>
				<a href="add${property}.jsp?id=${dlBracket}entity.${keyProp}}&flag=update">修改</a>    
				<a href="${property?uncap_first}.jsp?id=${dlBracket}entity.${keyProp}}" onclick="javascript:alert('你确定要删除这条数据吗?');"> 删除</a>
			</td>
        </tr> 
        </c:forEach> 
        </tbody>
    </table>
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
