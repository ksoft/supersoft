/**
 * Created by d on 2016/12/5.
 */
$(function () {
    var menu={
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
        buildMenuTree:function(){
            $.ajax({
                type: 'Post',
                url: 'getMenus',
                dataType: 'json',
                async: false,
                success: function (data) {
                    var menuTree=$('#menuTreeDiv').treeview({
                        data:data,
                        levels: 1,
                        showTags: true,
                        showIcon: false,
                        showCheckbox: false,
                        multiSelect: false,
                        onNodeChecked: function(event, node) {
                            console.log(node);
                            //alert(node.text);
                        },
                        onNodeUnchecked: function (event, node) {
                            //alert(node.text);
                        },
                        onNodeSelected: function(event, node) {
                            $("#menuName").text(node.text).attr("roleCode",node.roleCode);
                        },
                        onNodeUnselected: function (event, node) {
                            //alert(node.text);
                        }
                    });
                    menuTree.treeview('expandAll', { levels: 2, silent: $('#chk-expand-silent').is(':checked') });
                },
                error: function (err) {
                    alert('不好意思，数据忘记带上了。。。');
                }
            });
        },
        init:function(){
            menu.buildMenuTree();
            menu.buildRoleSelect();
        }
    }

    menu.init();


})