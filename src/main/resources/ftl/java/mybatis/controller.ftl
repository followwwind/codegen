package ${packageName!"com.wind.controller"};

<#assign lBracket = "<"/>
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
<#if swagger>import io.swagger.annotations.Api;${"\n"}</#if><#t>
<#if swagger>import io.swagger.annotations.ApiOperation;${"\n"}</#if><#t>
<#if primaryKeys?? && primaryKeys?size gt 0>
 <#assign key = getKey(columns, primaryKeys[0])>
 <#assign type = key.type>
</#if>

/**
 * @Title: ${property}Controller
 * @Package ${packageName!"com.wind.controller"}
 * @Description: ${remarks!""}接口
 * @author wind
 * @date ${.now?string("yyyy/MM/dd HH:mm:ss")}
 * @version V1.0
 */
@RestController
@RequestMapping(value = "${property?uncap_first}")
<#if swagger>@Api(value="${property?uncap_first}")${"\n"}</#if><#t>
public class ${property}Controller{
    <#assign service = property?uncap_first + "Service">
	
	private Logger logger = LoggerFactory.getLogger(${property}Controller.class);
	
    @Autowired
    private ${property}Service ${service};

    /**
     * 添加记录接口
     * ${property?uncap_first}/save
     * @param r
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 添加记录接口", notes="${property} 添加记录接口")")${"\n"}</#if><#t>
    public JsonResult save(Object r) {
    	logger.info("${property}Controller.save param: r is {}", r);
        int i = 0;
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    /**
     * 删除记录接口
     * ${property?uncap_first}/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 删除记录接口", notes="${property} 删除记录接口")${"\n"}</#if><#t>
    public JsonResult delete(@PathVariable("id") ${type!"String"} id) {
    	logger.info("${property}Controller.delete param: id is {}", id);
        int i = 0;
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    /**
     * 单条记录查询接口
     * ${property?uncap_first}/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 单条记录查询接口", notes="${property} 单条记录查询接口")${"\n"}</#if><#t>
    public JsonResult get(@PathVariable("id") ${type!"String"} id) {
    	logger.info("${property}Controller.get param: id is {}", id);
        return new JsonResult(null);
    }

    /**
     * 批量查询记录接口
     * ${property?uncap_first}/list
     * @param r
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 批量查询记录接口", notes="${property} 批量查询记录接口")${"\n"}</#if><#t>
    public JsonResult list(Object r) {
    	logger.info("${property}Controller.list param: r is {}", r);
        return new JsonResult(null);
    }

    /**
     * 分页查询记录接口
     * ${property?uncap_first}/page/list
     * @param r
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 分页查询记录接口", notes="${property} 分页查询记录接口")${"\n"}</#if><#t>
    public JsonResult pageList(Object r){
    	logger.info("${property}Controller.pageList param: r is {}", r);
        return new JsonResult(null);
    }

    /**
     * 修改记录接口
     * ${property?uncap_first}/update
     * @param r
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 修改记录接口", notes="${property} 修改记录接口")${"\n"}</#if><#t>
    public JsonResult update(Object r) {
    	logger.info("${property}Controller.update param: r is {}", r);
        int i = 0;
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }
}
<#function getKey columns primary>
 <#local b = {}>
 <#list columns as column>
  <#if primary.colName == column.columnName>
   <#local b = column>
  </#if>
 </#list>
 <#return b>
</#function>