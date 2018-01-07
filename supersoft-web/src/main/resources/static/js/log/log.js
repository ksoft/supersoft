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
        ,url: '/log/list' //数据接口
        ,method:'post'
        ,limit:20
        //,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width: '10%', sort: true}
            ,{field: 'userName', title: '姓名', width:'20%', sort: true}
            ,{field: 'ip', title: 'IP地址', width: '20%', sort: true}
            ,{field: 'createDt', title: '创建时间', width:'20%', sort: true}
            ,{field: 'createBy', title: '创建人', width: '10%', sort: true}
            ,{field: 'opt', title: '操作', width: '20%', sort: true, toolbar: '#operateBar'}
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
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                var ids=[];
                ids[0]=data.id;
                layer.close(index);
                $.ajax({
                    type:"POST",
                    url:"/log/delete",
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
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var queryParam = $('#queryParam');
            //执行重载
            table.reload('listTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    queryParam: queryParam.val()
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
                    url: "/log/delete",
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