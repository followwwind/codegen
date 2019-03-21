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
import org.springframework.web.bind.annotation.*;
<#if swagger>import io.swagger.annotations.Api;${"\n"}</#if><#t>
<#if swagger>import io.swagger.annotations.ApiOperation;${"\n"}</#if><#t>
<#if primaryKeys?? && primaryKeys?size gt 0>
 <#assign key = getKey(columns, primaryKeys[0])>
 <#assign type = key.type?replace("java.lang.", "")>
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
    @PostMapping("/")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 添加记录接口", notes="${property} 添加记录接口")")${"\n"}</#if><#t>
    public JsonResult save(@RequestBody ${property}Q r) {
    	logger.info("${property}Controller.save param: r is {}", r);
        return ${service}.save(r);
    }

    /**
     * 删除记录接口
     * ${property?uncap_first}/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 删除记录接口", notes="${property} 删除记录接口")${"\n"}</#if><#t>
    public JsonResult delete(@PathVariable("id") ${type!"String"} id) {
    	logger.info("${property}Controller.delete param: id is {}", id);
        return ${service}.delete(id);
    }

    /**
     * 单条记录查询接口
     * ${property?uncap_first}/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 单条记录查询接口", notes="${property} 单条记录查询接口")${"\n"}</#if><#t>
    public JsonResult get(@PathVariable("id") ${type!"String"} id) {
    	logger.info("${property}Controller.get param: id is {}", id);
        return ${service}.get(id);
    }

    /**
     * 批量查询记录接口
     * ${property?uncap_first}/list
     * @param r
     * @return
     */
    @PostMapping("/list")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 批量查询记录接口", notes="${property} 批量查询记录接口")${"\n"}</#if><#t>
    public JsonResult list(@RequestBody ${property}SearchQ r) {
    	logger.info("${property}Controller.list param: r is {}", r);
        return ${service}.list(r);
    }

    /**
     * 分页查询记录接口
     * ${property?uncap_first}/page/list
     * @param r
     * @return
     */
    @PostMapping("/page/list")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 分页查询记录接口", notes="${property} 分页查询记录接口")${"\n"}</#if><#t>
    public JsonResult pageList(@RequestBody ${property}SearchQ r){
    	logger.info("${property}Controller.pageList param: r is {}", r);
        return ${service}.pageList(r);
    }

    /**
     * 修改记录接口
     * ${property?uncap_first}/update
     * @param r
     * @return
     */
    @PutMapping("/")
    <#if swagger>${"\t"}@ApiOperation(value="${property} 修改记录接口", notes="${property} 修改记录接口")${"\n"}</#if><#t>
    public JsonResult update(@RequestBody ${property}Q r) {
    	logger.info("${property}Controller.update param: r is {}", r);
        return ${service}.update(r);
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