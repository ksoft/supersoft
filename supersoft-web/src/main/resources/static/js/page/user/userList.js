/**
 * Created by d on 2016/12/2.
 */
$(function () {
    var user={
        initTable:function(){
            $("#userListTable").bootstrapTable({
                    url: global.context+"/user/findUsersByPage",
                    method: 'post',      //请求方式（*）
                    search: true,
                    showRefresh: true,
                    showToggle: true,
                    showColumns: true,
                    showExport:true,//是否显示导出按钮
                    exportOptions:{
                        fileName:'用户列表',
                        tableName:'用户详细信息',
                        //displayTableName:true,
                        ignoreColumn: ['id'],//指定不导出哪些列
                        worksheetName:'用户工作表'
                    },
                    exportTypes:['txt', 'pdf','png','doc','xls','xlsx'],//导出类型
                    toolbar: '#toolbar',    //工具按钮用哪个容器
                    striped: true,      //是否显示行间隔色
                    cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true,     //是否显示分页（*）
                    sortable: true,      //是否启用排序
                    sortOrder: "asc",     //排序方式
                    queryParams: function (params) {
                        var params = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                            pageSize: params.limit, //页面大小
                            page: params.offset, //页码
                            sort: params.sort,
                            sortName:params.sortName,
                            order:params.order,
                            userName:$("#userName").val(),
                            userCode:$("#userCode").val(),
                            email:$("#email").val(),
                            roleCode:$("#roleCode").val()
                        };
                        return params;
                    },//传递参数（*）
                    sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: 1,      //初始化加载第一页，默认第一页
                    pageSize: 20,      //每页的记录行数（*）
                    pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
                    strictSearch: true,
                    clickToSelect: true,    //是否启用点击选中行
                    height: 460,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                    uniqueId: "id",      //每一行的唯一标识，一般为主键列
                    cardView: false,     //是否显示详细视图
                    detailView: false,     //是否显示父子表
                    onSort: function (name, order) {

                    },
                    columns: [{
                        field: 'id',
                        title: '序号',
                        checkbox:true
                    }, {
                        field: 'userCode',
                        sortName:'USER_CODE',
                        sortable: true,
                        title: '用户名',
                        width:100
                    }, {
                        field: 'userName',
                        sortName:'USER_NAME',
                        sortable: true,
                        title: '姓名',
                        width:100
                    }, {
                        field: 'passWord',
                        sortName:'PASSWORD',
                        sortable: true,
                        title: '密码',
                        width:100
                    }, {
                        field: 'email',
                        sortName:'EMAIL',
                        sortable: true,
                        title: 'Email',
                        width:100
                    }]
            }).colResizable();
        },
        buildRoleSelect:function(){
            $("#roleSelect").select2({
                placeholder:"请选择角色",
                minimumInputLength: 0,
                maximumInputLength:50,
                allowClear:true,
                width:210,
                language: "zh-CN",
                ajax: {
                    url: global.context+'/role/getAllRolesByPage',
                    contentType : "application/json",
                    dataType: 'json',
                    type:'POST',
                    delay: 500,
                    async:false,
                    timeout : 3000, //超时时间设置，单位毫秒
                    data: function (params) {
                        var queryData={};
                        queryData.queryStr=params.term;
                        queryData.page=params.page;
                        queryData.pageSize=10;
                        return JSON.stringify(queryData);
                    },
                    processResults: function (data,params) {
                        params.page = params.page || 1;
                        var results = [];
                        $.each(data.rows, function (i, v) {
                            var o = {};
                            o.id = v.roleCode;
                            o.text=v.roleName;
                            o.roleCode=v.roleCode;
                            o.roleName = v.roleName;
                            results.push(o);
                        });
                        console.log(results);
                        return {
                            results: results,
                            pagination: {
                                more: (params.page * 10) < data.total //每页10条数据
                            }
                        };
                    },
                    cache: true
                },
                escapeMarkup: function (markup) { return markup; },
                templateResult: function formatRepo(repo){
                    if(!repo.id){
                        return "加载中...";
                    }
                    return '<span style="color:black">' + repo.roleName + '</span>';
                },
                templateSelection: function formatRepoSelection(repo, container){
                    if(!repo.id){
                        return "请选择角色";
                    }
                    return repo.text
                }
            });
        },
        init:function(){
            this.initTable();
            this.buildRoleSelect();
        }
    }

    user.init();

    //查询
    $("#query").click(function(){
        $("#userListTable").bootstrapTable('refresh');
    });
});
