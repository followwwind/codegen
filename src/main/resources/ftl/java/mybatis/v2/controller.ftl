package ${packageName!"com.wind.controller"};

<#assign lBracket = "<"/>
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
<#if swagger>import io.swagger.annotations.Api;${"\n"}</#if><#t>
<#if swagger>import io.swagger.annotations.ApiOperation;${"\n"}</#if><#t>

/**
 * ${remarks!""} controller
 * @author wind
 */
@RestController
@RequestMapping(value = "${property?uncap_first}")
<#if swagger>@Api(value="${property?uncap_first}")${"\n"}</#if><#t>
public class ${property}Controller{
    <#assign service = property?uncap_first + "Service">

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
    public JsonResult save(${property} r) {
        int i = ${service}.insert(r);
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    /**
     * 删除记录接口
     * ${property?uncap_first}/delete
     * @param r
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 删除记录接口", notes="${property} 删除记录接口")${"\n"}</#if><#t>
    public JsonResult delete(${property} r) {
        int i = ${service}.delete(r);
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    /**
     * 单条记录查询接口
     * ${property?uncap_first}/findEntity
     * @param r
     * @return
     */
    @RequestMapping(value = "findEntity", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 单条记录查询接口", notes="${property} 单条记录查询接口")${"\n"}</#if><#t>
    public JsonResult find${property}(${property} r) {
        return new JsonResult(${service}.findEntity(r));
    }

    /**
     * 批量查询记录接口
     * ${property?uncap_first}/findList
     * @param r
     * @return
     */
    @RequestMapping(value = "findList", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 批量查询记录接口", notes="${property} 批量查询记录接口")${"\n"}</#if><#t>
    public JsonResult findList(${property} r) {
        return new JsonResult(${service}.findList(r));
    }

    /**
     * 分页查询记录接口
     * ${property?uncap_first}/findPageList
     * @param r
     * @return
     */
    @RequestMapping(value = "findPageList", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 分页查询记录接口", notes="${property} 分页查询记录接口")${"\n"}</#if><#t>
    public JsonResult findPageList(${property} r, Page page){
    	${service}.findPageList(r, page);
        return new JsonResult(page);
    }

    /**
     * 修改记录接口
     * ${property?uncap_first}/update
     * @param r
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    <#if swagger>${"\t"}@ApiOperation(value="${property} 修改记录接口", notes="${property} 修改记录接口")${"\n"}</#if><#t>
    public JsonResult update(${property} r) {
        int i = ${service}.update(r);
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }
}