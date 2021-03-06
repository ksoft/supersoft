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
        ,url: '/user/list' //数据接口
        ,method:'post'
        ,limit:20
        //,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID', width:'2%', sort: true}
            ,{field: 'userCode', title: '用户名', width:'5%', sort: true}
            ,{field: 'userName', title: '姓名', width:'5%', sort: true}
            ,{field: 'sex', title: '性别', width:'5%', templet: '#sexTpl', unresize: true, sort: true}
            ,{field: 'email', title: '邮箱', width:'15%', sort: true}
            ,{field: 'mobilePhone', title: '手机号', width: '10%', sort: true}
            ,{field: 'roleName', title: '角色', width: '10%', sort: true}
            ,{field: 'createDt', title: '创建时间', width:'10%', sort: true}
            ,{field: 'createBy', title: '创建人', width: '10%', sort: true}
            ,{field: 'status', title: '状态', templet: '#statusTpl', unresize: true, width: '5%', sort: true}
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
    //监听 新增
    form.on('submit(formAdd)', function(data){
        $.ajax({
            type:"POST",
            url:"/user/save",
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
            url:"/user/update",
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

    //监听 修改密码
    form.on('submit(updatePwd)', function(data){
        $.ajax({
            type:"POST",
            url:"/user/updatePwd",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(res){
                layer.msg(res.message);
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
                content: '/user/view/'+data.id,
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
                    url:"/user/delete",
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
                content: '/user/edit/'+data.id,
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
                title:"新增用户",
                area: ['100%', '100%'],
                content: '/user/add',
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
                content: '/user/edit/'+id,
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
                    url: "/user/delete",
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

    var uploadInst =upload.render({
        elem: '#headIconImg'
        ,url: '/file/upload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#headIconImg').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }else{
                //上传成功
                $('#headIcon').val(res.data);
            }
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#uploadMsg');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    form.verify({
        userName: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        ,password: [
            /^[\S]{5,12}$/
            ,'密码必须5到12位，且不能出现空格'
        ]
    });
});