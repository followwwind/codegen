package ${packageName!"com.wind.service.impl"};

import java.util.List;
<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import java.util.List;
<#if primaryKeys?? && primaryKeys?size gt 0>
    <#assign key = getKey(columns, primaryKeys[0])>
    <#assign type = key.type?replace("java.lang.", "")>
<#else>
    <#assign type = "String">
</#if>

/**
 * @Title: ${property}ServiceImpl
 * @Package ${packageName!"com.wind.service.impl"}
 * @Description: ${remarks!""}业务处理
 * @author wind
 * @date ${.now?string("yyyy/MM/dd HH:mm:ss")}
 * @version V1.0
 */
@Service
public class ${property}ServiceImpl implements ${property}Service{

	private Logger logger = LoggerFactory.getLogger(${property}ServiceImpl.class);

    @Autowired
    private ${property}Mapper mapper;

    @Override
    public JsonResult save(${property}Q r) {
    	logger.info("${property}ServiceImpl.save param: r is {}", r);
        ${property} entity = new ${property}();
        BeanUtil.copy(r, entity);
        int i = mapper.insert(entity);
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    @Override
    public JsonResult delete(${type!"String"} id) {
    	logger.info("${property}ServiceImpl.delete param: id is {}", id);
        int i = mapper.deleteById(id);
        return new JsonResult(i > 0 ? HttpCode.OK : HttpCode.FAIL);
    }

    @Override
    public JsonResult get(${type!"String"} id) {
    	logger.info("${property}ServiceImpl.get param: id is {}", id);
        ${property} r = mapper.findById(id);
        return new JsonResult(HttpCode.OK, r);
    }

    @Override
    public JsonResult list(${property}SearchQ r) {
    	logger.info("${property}ServiceImpl.list param: r is {}", r);
        List<${property}VO> list = mapper.list(r);
        return new JsonResult(HttpCode.OK, list);
    }

    @Override
    public JsonResult pageList(${property}SearchQ r){
    	logger.info("${property}ServiceImpl.pageList param: r is {}", r);
        PageHelper.startPage(r.getPageNumber(), r.getLineNumber());
        List<${property}VO> list = mapper.list(r);
        Page<${property}VO> page = new Page<>(list);
        return new JsonResult(HttpCode.OK, page);
    }

    @Override
    public JsonResult update(${property}Q r) {
    	logger.info("${property}ServiceImpl.update param: r is {}", r);
        ${property} entity = new ${property}();
        BeanUtil.copy(r, entity);
        int i = mapper.update(entity);
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
