/**
 * Created by 李朝衡 on 2017/3/31.
 */
var serviceMode = 'DRIVER_TO_POINT';
var $driverPhone = $(".driver-info").find("input[name='driverPhone']");
var $driverName = $(".driver-info").find("input[name='driverName']");
var $plateNum = $(".driver-info").find("input[name='plateNum']");

// 创建Map实例
var choosePoint,zoomLevel = 8;
var map;
$(function(){
    $("#add-link").addClass("link-active");
    $('input[name="serviceMode"]').on('ifChecked', function(event){
        serviceMode = $(this).val();
    });

    $(".driver-info").find("input").on("keyup",function () {
        $(this).removeClass("input-err");
    });
    /**
     * 提交订单
     */
    $("#create-order").click(function () {
        if(!checkDriverInfo()){
            return;
        }
        $.ajax({
            url:"/orders",
            type:'post',
            data:{
                province:$("input[name='province']").val(),
                driverPhone:$("input[name='driverPhone']").val(),
                plateNum:$("input[name='plateNum']").val().toUpperCase(),
                driverName:$("input[name='driverName']").val(),
                servicePointId:$("input[name='servicePointId']").val(),
                // serviceMode:serviceMode,
            },
            success:function (result) {
                if(result.success){
                    window.location.href = "/orders";
                }
            }
        });
    });
    $("button#btn-cancel").click(function () {
        window.location.href = "/orders";
    });
    $( "#search-input" ).autocomplete({
        minLength: 0,
        appendTo:"#search-append",
        source: "/points/search",
        focus: function( event, ui ) {
            return false;
        },
        select: function( event, ui ) {
            // var options = {
            //     geotableId: geoTableId,
            //     q: '', //检索关键字
            //     tags: '', //空格分隔的多字符串
            //     filter: 'store_id:['+ui.item.storeId+"]"//过滤条件,参考http://developer.baidu.com/map/lbs-geosearch.htm#.search.nearby
            // };
            // buildMapLayer(options);
            // createMap(points);
            var point = new BMap.Point(ui.item.longitude,ui.item.latitude);
            var pd = {
                point:point,
                storeName:ui.item.storeName,
                storePhone:ui.item.storePhone,
                storeContact:ui.item.storeContact,
                description:ui.item.description,
                storeId:ui.item.storeId,
                province:ui.item.province,
            };
            choosePoint = pd;
            $("#sm-all").iCheck("check");
            buildInfoWindow(pd,point);
            // map.centerAndZoom(point, map.getZoom());
            return false;
        }
    }).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li title='"+item.storeName+"'>" )
            .append(item.storeName)
            .appendTo( ul );
    };
    $(".service-mode").on("change",function () {
        if ($(this).val() == 'ALL') {
            createMap(points);
        } else if ($(this).val() == 'WAIT_TO_RESCUE') {
            createMap(carPoints);
        } else {
            createMap(areaPoints);
        }
    });
    /**
     * layer
     */
    $("button.open-map").click(function () {
        $("#search-input").val(null);
        if(!checkDriverInfo()){
            return;
        }
        $.ajax({
            type:'post',
            url:"/orders/record",
            async:false,
            data:{plateNum:$plateNum.val()},
            success:function (result) {
                if(!result.success){
                    $(".err-msg").show();
                    return;
                }
                $(".err-msg").hide();
                $("#map-select").show();
                layer.open({
                    type:1,
                    title:false,
                    content:$("#map-select"),
                    area:['1200px','710px'],
                    offset:['20px'],
                    cancel:function () {
                        $("#map-select").hide();
                        $(".service-mode").removeAttr("checked");
                        $(".service-mode").eq(0).attr("checked",true);
                    },
                    end:function () {
                        $("#map-select").hide();
                        $(".service-mode").removeAttr("checked");
                        $(".service-mode").eq(0).attr("checked",true);
                    },
                    success:function () {
                        $(".service-mode").iCheck({
                            checkboxClass: 'icheckbox_flat-red',
                            radioClass: 'iradio_flat-red',
                        }).on("ifChecked",function () {
                            if ($(this).val() == 'ALL') {
                                createMap(points);
                            } else if ($(this).val() == 'WAIT_TO_RESCUE') {
                                createMap(carPoints);
                            } else {
                                createMap(areaPoints);
                            }
                        });
                    }
                });
                createMap(points);
            }
        });
    });
    $('.menuDiv').click(function(){
        $(this).children('b').addClass('menuLine');
        $(this).siblings().children('b').removeClass('menuLine')
    });

    // $('.contentInfoBut a:first-child').css('margin-right','0');
    // $('.contentTable li:first-child').css('color','#6dadff');
    // $('.contentTit b:last').css('border-bottom','2px #3e91fc solid');
});

function shakeInput(obj) {
    obj.stop()
        .animate({left: "-5px"}, 70)
        .animate({left: "5px"}, 70)
        .animate({left: "-5px"}, 70)
        .animate({left: "5px"}, 70)
}
function checkDriverInfo() {
    if($plateNum.val()==''){
        $plateNum.addClass("input-err");
        shakeInput($plateNum);
        $plateNum.focus();
        return false;
    }
    if($driverName.val()==''){
        $driverName.addClass("input-err");
        shakeInput($driverName);
        $driverName.focus();
        return false;
    }
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    if(!reg.test($driverPhone.val())){
        layer.tips("手机号不合法",$driverPhone,{tips:[3,"#cc1f61"]});
        $driverPhone.addClass("input-err");
        shakeInput($driverPhone);
        $driverPhone.focus();
        return false;
    }
    if($driverPhone.val()==''){
        $driverPhone.addClass("input-err");
        shakeInput($driverPhone);
        $driverPhone.focus();
        return false;
    }
    return true;
}
/**
 * 创建map
 * @param ps
 */
function createMap(ps) {
    // map.reset();
     map = new BMap.Map("allmap");
    var point= new BMap.Point(120.277221, 30.297788);
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.enableScrollWheelZoom(true);
    // var cityName ="杭州";
    // function myFun(result){
    //      cityName = result.name;
    // }
    // var myCity = new BMap.LocalCity();
    // myCity.get(myFun);
    map.centerAndZoom(point, zoomLevel);  // 初始化地图,设置中心点坐标和地图级别
    map.addEventListener("zoomend",function (type,target) {
       zoomLevel = map.getZoom();
    });
    $.each(ps, function (i, v) {
        var poi = new BMap.Point(v.longitude, v.latitude);
        var icon;
        if (v.storeType == "SERVICE_CAR") {
            icon = carXIcon;
        } else {
            icon = signedIcon;
        }
        var marker = new BMap.Marker(poi,{icon:icon});  // 创建标注
        map.addOverlay(marker);               // 将标注添加到地图中
        marker.addEventListener("click",function (type,target) {
            choosePoint = v;
            buildInfoWindow(v,poi);
        });
    });
}
function buildInfoWindow(v,poi) {
    var pic;
    if (v.storeType == 'SERVICE_AREA') {
        pic = staticPath + "/img/area.png";
    } else {
        pic = staticPath + "/img/car.png";
    }
    var content = '<div class="myPop">' +
        '<div class="myPop_2"> <img src="'+pic+'" class="myPopCar"> ' +
        '<p class="pop-text"><span>方向：</span>'+v.description+'</p>'+
        '<p class="pop-text"><span>联系人：</span>'+v.storeContact+'</p>'+
        '<p class="pop-text"><span>联系方式：</span>'+v.storePhone+'</p>'+
        '<p class="pop-text"><button onclick="selectPoint('+v.storeId+')" ' +
        'data-pointId="'+v.storeId+'" class="btn btn-success point-select-btn">指派</button></p>'+
        // '<p class="myPopWu">暂无签约</p>' +
        '</div></div>';
    var infoWindow = new BMap.InfoWindow(content,
        {title:"<div class='store-title'>"+v.storeName+"</div>"}
    );  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,poi);//显示信息窗口
    map.centerAndZoom(poi, map.getZoom());//居中
    // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}
/**
 * 选择服务点
 */
function selectPoint() {
    var $point = $("div#point-info");
    $point.find("span.point-name").text("服务区名称："+choosePoint.storeName);
    $point.find("span.point-contact").text("服务区联系人："+choosePoint.storeContact);
    $point.find("span.point-phone").text("服务区联系电话："+choosePoint.storePhone);
    $("input[name='province']").val(choosePoint.province);
    $("input[name='servicePointId']").val(choosePoint.storeId);
    $point.removeClass("hide");
    layer.closeAll();
}
