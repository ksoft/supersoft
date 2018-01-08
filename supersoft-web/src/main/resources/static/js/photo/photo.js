layui.use(['jquery','layer','form','table','upload'],function(){
    'use strict';
    var $ = layui.jquery
        ,layer=layui.layer
        ,form =layui.form
        ,table = layui.table
        ,upload = layui.upload;

    //监听 新增
    form.on('submit(formAdd)', function(data){
        $.ajax({
            type:"POST",
            url:"/photo/save",
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

    var uploadInst =upload.render({
        elem: '#photo'
        ,url: '/file/upload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#photo').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }else{
                //上传成功
                $('#imgSrc').val(res.data);
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
});