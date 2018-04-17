package ${package!"com.wind"}.controller;

import java.util.List;
import net.sf.json.JSONObject;
import ${package!"com.wind"}.entity.${property};
import ${package!"com.wind"}.service.${property}Service;
import ${package!"com.wind"}.entity.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


/**
 * ${remarks!""} controller
 * @author wind
 */
@RestController
@RequestMapping(value = "${property?uncap_first}")
@Api(value="${property?uncap_first}")
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
    @ApiOperation(value="${property} 添加记录接口", notes="${property} 添加记录接口")
    public String save(${property} r) {
        int i = ${service}.insert(r);
        JSONObject result = new JSONObject();
        result.put("msg", i > 0 ? "success" : "failure");
        return result.toString();
    }

    /**
     * 删除记录接口
     * ${property?uncap_first}/delete
     * @param r
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation(value="${property} 删除记录接口", notes="${property} 删除记录接口")
    public String delete(${property} r) {
        int i = ${service}.deleteByCondition(r);
        JSONObject result = new JSONObject();
        result.put("msg", i > 0 ? "success" : "failure");
        return result.toString();
    }

    /**
     * 单条记录查询接口
     * ${property?uncap_first}/findEntity
     * @param r
     * @return
     */
    @RequestMapping(value = "findEntity", method = RequestMethod.POST)
    @ApiOperation(value="${property} 单条记录查询接口", notes="${property} 单条记录查询接口")
    public ${property} find${property}(${property} r) {
        return ${service}.findEntity(r);
    }

    /**
     * 批量查询记录接口
     * ${property?uncap_first}/findList
     * @param r
     * @return
     */
    @RequestMapping(value = "findList", method = RequestMethod.POST)
    @ApiOperation(value="${property} 批量查询记录接口", notes="${property} 批量查询记录接口")
    public List<${property}> findByCondition(${property} r) {
        return ${service}.findByCondition(r);
    }

    /**
     * 分页查询记录接口
     * ${property?uncap_first}/findPageList
     * @param r
     * @return
     */
    @RequestMapping(value = "findPageList", method = RequestMethod.POST)
    @ApiOperation(value="${property} 分页查询记录接口", notes="${property} 分页查询记录接口")
    public Page findPageList(${property} r, Page page){
    	${service}.findPageList(r, page);
        return page;
    }

    /**
     * 修改记录接口
     * ${property?uncap_first}/update
     * @param r
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value="${property} 修改记录接口", notes="${property} 修改记录接口")
    public String updateByCondition(${property} r) {
        int i = ${service}.updateByCondition(r);
        JSONObject result = new JSONObject();
        result.put("msg", i > 0 ? "success" : "failure");
        return result.toString();
    }
}

