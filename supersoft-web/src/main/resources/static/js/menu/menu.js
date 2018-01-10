layui.use(['jquery','layer','form','table','upload'],function(){
    'use strict';
    var $ = layui.jquery
        ,layer=layui.layer
        ,form =layui.form
        ,table = layui.table;

    //第一个实例
    table.render({
        elem: '#listTable'
        ,id: 'listTable'
        ,height: 'full-100' //高度最大化减去差值
        ,url: '/menu/list' //数据接口
        ,method:'post'
        ,limit:20
        //,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width:'5%', sort: true}
            ,{field: 'title', title: '菜单名称', width:'20%', sort: true}
            ,{field: 'icon', title: '图标',templet:'#iconTpl',unresize:true, width: '5%', sort: true}
            ,{field: 'type', title: '菜单类型', templet: '#typeTpl', unresize: true, width: '10%', sort: true}
            ,{field: 'createDt', title: '创建时间', width:'15%', sort: true}
            ,{field: 'createBy', title: '创建人', width: '15%', sort: true}
            ,{field: 'status', title: '状态', templet: '#statusTpl', unresize: true, width: '5%', sort: true}
            ,{field: 'opt', title: '操作', width: '20%', sort: true, toolbar: '#operateBar'}
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
    //监听菜单类别操作
    form.on('radio(typeFilter)', function(obj){
        form.render("select");
        $("select").find("option:selected").attr('selected',false);
        if(this.value == 'LEFT'){
           $("#parent").show();
        }else{
            $("#parent").hide();
        }
    });
    //监听 新增
    form.on('submit(formAdd)', function(data){
        $.ajax({
            type:"POST",
            url:"/menu/save",
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
            url:"/menu/update",
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
                content: '/menu/view/'+data.id,
                end: function () {
                    active.reload();
                }
            });
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                var ids=[];
                ids[0]=data.id;
                layer.close(index);
                $.ajax({
                    type:"POST",
                    url:"/menu/delete",
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
                content: '/menu/edit/'+data.id,
                end: function () {
                    active.reload();
                }
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
        add: function(){
            layer.open({
                type: 2,
                title:"新增",
                area: ['100%', '100%'],
                content: '/menu/add',
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
                content: '/menu/edit/'+id,
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
                    url: "/menu/delete",
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

     function decode(str) {
         return unescape(str.replace(/&#x/g,'%u').replace(/;/g,''))
     }

    var iconLayerIndex;
    $("#iconAdd").on('click',function () {
        iconLayerIndex=layer.open({
            type: 1,
            title:"请选择菜单图标",
            area: ['80%', '80%'],
            content: $('#iconMenuPage')
        });
    });

    $(".site-doc-icon > li").on('click',function () {
        var iconCode=$(this).find("div.code").text();
        $("#iconPreview").text(decode(iconCode));
        $("#icon").val(iconCode);
        layer.close(iconLayerIndex);
    });
});