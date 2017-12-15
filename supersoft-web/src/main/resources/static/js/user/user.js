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
        elem: '#demo'
        ,id: 'testReload'
        ,height: 'full-100' //高度最大化减去差值
        ,url: '/user/list' //数据接口
        ,method:'post'
        ,limit:20
        ,where: {tokensdf: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id',type:'checkbox', title: 'ID', width:80, sort: true, fixed: 'left'}
            ,{field: 'userName', title: '用户名s3', width:180}
            ,{field: 'email', title: '性别', width:180, sort: true}
            ,{field: 'city', title: '城市', width:180}
            ,{field: 'sign', title: '签名', width: 177}
            ,{field: 'experience', title: '积分', width: 180, sort: true}
            ,{field: 'score', title: '评分', width: 180, sort: true}
            ,{field: 'classify', title: '职业', width: 180}
            ,{field: 'wealth', title: '财富', width: 350, sort: true, toolbar: '#barDemo'}
        ]]
    });

    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});