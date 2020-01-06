'use strict'
app.controller('${property}Controller', function($scope, $http, $timeout, time, obj, $stateParams) {

    $scope.query = {
        "lineNumber": 10,
        "pageNumber": 1,
        "name": ""
    };

    $scope.form = {
        "flag": 1, //1表示新增，2表示修改
        "title": '新增',
        "entity": {
            <#list columns as column>
            '${column.property}',
            </#list>
        }
    };

    $scope.body = {
        "queryTitle": "查询条件",
        "tableTitle": "数据列表"
    };

    $scope.returnData = {
        recordsTotal: 0,
        recordsFiltered: 0,
        data: []
    };


    var tbl;

    $timeout(function(){
        tbl = $('#tbl').DataTable({
            "ajax" : function (data, callback, settings) {
                //封装请求参数
                $scope.query.lineNumber = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                $scope.query.pageNumber = (data.start / data.length)+1;//当前页码
                $http.post($scope.serverUrl + 'api/${property?uncap_first}/page/list', $scope.query).then(function(response){
                    // console.log(response.data);
                    var resultData = response.data.data;
                    $scope.returnData.recordsTotal = resultData.totalCount;//返回数据全部记录
                    $scope.returnData.recordsFiltered = resultData.totalCount;//后台不实现过滤功能，每次查询均视作全部结果
                    $scope.returnData.data = resultData.data;//返回的数据列表
                    callback($scope.returnData);
                },
                function(err){
                    layui.layer.msg('数据加载请求异常', {icon: 2});
                });
            },
            "serverSide":true,
            "language":$scope.datatables_lang, //提示信息
            "renderer": "bootstrap", //渲染样式：Bootstrap和jquery-ui
            "pagingType": "full_numbers", //分页样式：simple,simple_numbers,full,full_numbers
            "bFilter": true,
            "bSort": false,
            /*"scrollX": true,*/
            "fnInitComplete":function (oSettings, json) {

            },
            "columns": [
                {
                    "targets": 0,
                    "render": function(data, type, row){
                        return '<label class="i-checks m-b-none"><input type="checkbox"><i></i></label>';
                    }
                },
                <#list columns as column>
                    <#if contains(primaryKeys, column.columnName) == false>
                {"data": "${column.property}"},
                    </#if>
                </#list>
                //{
                //    "render": function(data, type, row){
                //        return time.format(row.createTime);
                //    }
                //},
                {
                    "render": function(data, type, row){
                        var html = '<span class="btn btn-info btn-sm" name="edit">编辑</span>';
                        html += '<span class="btn btn-danger btn-sm" style="margin-left:3px;" name="del">删除</span>';
                        return html;
                    }
                },
            ]
        })
    }, 0);

    $('#tbl tbody').on('click', 'span[name="edit"]', function(e){
        var entity = tbl.row($(e.target).parents("tr")).data();
        $scope.form.flag = 2;
        $scope.form.title = '修改';
        obj.copy(entity, $scope.form.entity);
        $scope.$apply();
        $("#add").modal("show");
    });

    $('#tbl tbody').on('click', 'span[name="del"]', function(e){
        var entity = tbl.row($(e.target).parents("tr")).data();
        $http.delete($scope.serverUrl + "api/${property?uncap_first}/" + entity.id, {}).then(function(response){
            if(response.data.code == 200){
                layui.layer.msg('删除成功', {icon: 1});
            }else{
                layui.layer.msg('删除失败', {icon: 2});
            }
            tbl.ajax.reload();
        },
        function(err){
            layui.layer.msg('服务器异常', {icon: 2});
        });
    });

    $scope.add = function(){
        $scope.form.flag = 1;
        $scope.form.title = '新增';
        $scope.form.entity = {};
        $("#add").modal("show");
    };

    $scope.search = function(){
        tbl.ajax.reload();
    };

    $scope.reset = function(){
        $scope.query = {
            "lineNumber": 10,
            "pageNumber": 1
        };
        tbl.ajax.reload();
    };

    $scope.save = function(){
        var url =  $scope.form.flag == 1 ? "api/${property?uncap_first}/save" : "api/${property?uncap_first}/update";
        $http.post($scope.serverUrl + url, $scope.form.entity).then(function(response){
            if(response.data.code == 200){
                layui.layer.msg('操作成功', {icon: 1});
            }else{
                layui.layer.msg('操作失败', {icon: 2});
            }
            tbl.ajax.reload();
            $("#add").modal("hide");
        },
        function(err){
            layui.layer.msg('服务器异常', {icon: 2});
        });
    };

    $scope.uploadImg = function(event){
        console.log(event);
        var param = new FormData();
        var files = event.target.files;
        for(var i = 0; i < files.length; i++){
            param.append("files", files[0]);
        }
        $http.post($scope.serverUrl + 'api/file/uploadFile', param, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function(response){
            // console.log(response);
            $scope.form.entity.img = response.data.data;
        }, function(err){

        });
    };


});

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