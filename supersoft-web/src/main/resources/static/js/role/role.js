layui.use(['jquery','layer','form','table','upload'],function(){
    'use strict';
    var $ = layui.jquery
        ,layer=layui.layer
        ,form =layui.form
        ,table = layui.table
        ,upload = layui.upload;

    //第一个实例
    table.render({
        elem: '#listTable'
        ,id: 'listTable'
        ,height: 'full-100' //高度最大化减去差值
        ,url: '/role/list' //数据接口
        ,method:'post'
        ,limit:20
        //,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width:80, sort: true}
            ,{field: 'name', title: '名称', width:180, sort: true}
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
    //监听状态操作
    form.on('switch(statusFilter)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    });
    //监听 新增
    form.on('submit(formAdd)', function(data){
        $.ajax({
            type:"POST",
            url:"/role/save",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(res){
                layer.msg(res.message)
                var index=parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
        return false;
    });

    //监听 修改
    form.on('submit(formEdit)', function(data){
        $.ajax({
            type:"POST",
            url:"/role/update",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(res){
                layer.msg(res.message)
                if(res.success){
                    var index=parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
            }
        });
        return false;
    });

    //监听工具条
    table.on('tool(operateFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: '/role/view/'+data.id
            });
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                var ids=[];
                ids[0]=data.id;
                layer.close(index);
                $.ajax({
                    type:"POST",
                    url:"/role/delete",
                    data: JSON.stringify(ids),
                    dataType:"json",
                    contentType:"application/json",
                    success:function(res){
                        obj.del();
                        layer.msg(res.message);
                        active.reload();
                    }
                });
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: '/role/edit/'+data.id
            });
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var queryParam = $('#queryParam');
            //执行重载
            table.reload('table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    queryParam: queryParam.val()
                }
            });
        },
        add: function(){
            layer.open({
                type: 2,
                title:"新增",
                area: ['100%', '100%'],
                content: '/role/add',
                end: function () {
                    active.reload();
                }
            });
        },
        edit: function(){
            var checkStatus = table.checkStatus('listTable')
                ,data = checkStatus.data;
            if(data.length==0){
                layer.alert("至少选中一条数据");
                return;
            }else if(data.length>1){
                layer.alert("只能选中一条数据");
                return;
            }
            var id=data[0].id;
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: '/role/edit/'+id,
                end: function () {
                    active.reload();
                }
            });
        },
        delete: function(){
            var checkStatus = table.checkStatus('listTable')
                ,data = checkStatus.data;
            if(data.length==0){
                layer.alert("至少选中一条数据");
                return;
            }
            var ids=[];
            for(var i=0;i<data.length;i++){
               ids[i]=data[i].id;
            }
            layer.confirm('真的删除选中的所有行么', function(index) {
                layer.close(index);
                $.ajax({
                    type: "POST",
                    url: "/role/delete",
                    data: JSON.stringify(ids),
                    dataType: "json",
                    contentType: "application/json",
                    success: function (res) {
                        layer.msg(res.message);
                        active.reload();
                    }
                });
            });
        }
    };

    $('.headerTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});