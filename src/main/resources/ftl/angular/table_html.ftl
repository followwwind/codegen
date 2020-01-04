<div ng-controller="${property}Controller" class="wrapper-md">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">{{body.queryTitle}}</h3>
        </div>
        <div class="panel-body">
            <div class="row col-md-12">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="">名称:</label>
                        <input type="text" class="form-control"  placeholder="" ng-model="query.name">
                    </div>
                    <!-- <div class="form-group">
                      <label for="">Email</label>
                      <input type="email" class="form-control"  placeholder="">
                    </div> -->
                    <button type="button" class="btn btn-success" ng-click="search()">搜索</button>
                </form>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">{{body.tableTitle}}</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3">
                    <button class="btn btn-success" ng-click="add()">新增</button>
                </div>
            </div>
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped m-b-none" id="tbl">
                        <thead>
                        <tr>
                            <th class="text-center">
                                <label class="i-checks m-b-none">
                                    <input type="checkbox" id="chkAll"><i></i>
                                </label>
                            </th>
                            <#list columns as column>
                                <#if contains(primaryKeys, column.columnName) == false>
                            <th class="text-center">${column.remarks}</th>
                                </#if>
                            </#list>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody class="text-center">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">{{form.title}}</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <#list columns as column>
                            <#if contains(primaryKeys, column.columnName) == false>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">${column.property}:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="${column.property}" ng-model="form.entity.${column.property}">
                            </div>
                        </div>
                            </#if>
                        </#list>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" ng-click="save()">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>

<#function contains primaryKeys colName>
    <#local b = false>
    <#list primaryKeys![] as primaryKey>
        <#if primaryKey.colName = colName>
            <#local b = true>
            <#break>
        </#if>
    </#list>
    <#return b>
</#function>