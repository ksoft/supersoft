/**
 * @Author: Larry  2017-04-16 17:20:56
 *+----------------------------------------------------------------------
 *| LarryBlogCMS [ LarryCMS网站内容管理系统 ]
 *| Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
 *| Version 1.09
 *| <313492783@qq.com>
 *+----------------------------------------------------------------------
 */
layui.use(['jquery','layer','form','table'],function(){
    'use strict';
    var $ = layui.jquery
        ,layer=layui.layer
        ,form =layui.form
        ,table = layui.table;

    //第一个实例
    table.render({
        elem: '#userTable'
        ,id: 'userTable'
        ,height: 'full-100' //高度最大化减去差值
        ,url: '/user/list' //数据接口
        ,method:'post'
        ,limit:20
        //,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width:80, sort: true}
            ,{field: 'userCode', title: '用户名', width:180, sort: true}
            ,{field: 'userName', title: '姓名', width:180, sort: true}
            ,{field: 'sex', title: '性别', width:80, templet: '#sexTpl', unresize: true, sort: true}
            ,{field: 'email', title: '邮箱', width:180, sort: true}
            ,{field: 'mobilePhone', title: '手机号', width: 177, sort: true}
            ,{field: 'createDt', title: '创建时间', width:180, sort: true}
            ,{field: 'createBy', title: '创建人', width: 177, sort: true}
            ,{field: 'status', title: '状态', templet: '#statusTpl', unresize: true, width: 160, sort: true}
            ,{field: 'opt', title: '操作', width: 250, sort: true, toolbar: '#operateBar'}
        ]]
    });

    //监听表格复选框选择
    table.on('checkbox(operate)', function(obj){
        console.log(obj)
    });
    //监听性别操作
    form.on('switch(sexFilter)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    });
    //监听状态操作
    form.on('switch(statusFilter)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    });
    //监听工具条
    table.on('tool(operateFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.alert('编辑行：<br>'+ JSON.stringify(data))
        }
    });

    var $ = layui.$, active = {
        getCheckData: function(){ //获取选中数据
            var checkStatus = table.checkStatus('idTest')
                ,data = checkStatus.data;
            layer.alert(JSON.stringify(data));
        }
        ,getCheckLength: function(){ //获取选中数目
            var checkStatus = table.checkStatus('idTest')
                ,data = checkStatus.data;
            layer.msg('选中了：'+ data.length + ' 个');
        }
        ,isAll: function(){ //验证是否全选
            var checkStatus = table.checkStatus('idTest');
            layer.msg(checkStatus.isAll ? '全选': '未全选')
        }
    };


    var $ = layui.$, active = {
        reload: function(){
            var queryParam = $('#queryParam');
            //执行重载
            table.reload('userTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    queryParam: queryParam.val()
                }
            });
        }
    };

    $('.queryTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});