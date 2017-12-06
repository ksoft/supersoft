/**
 * Created by 李朝衡 on 2017/3/31.
 */
var $table = $("table#order-list1");
var currentPage= 1,currentSize =10;
$(function(){
    $("#list-link").addClass("link-active");
    /**
     * 搜索
     */
    $(".btn-search").on("click",function () {
        var data = getData();
        data.offset=0;
        $table.bootstrapTable("selectPage",1);
        // $table.bootstrapTable("refresh",{query:data});
    });
    /**
     * 导出
     */
    $(".btn-export").on("click",function () {
        var regionId = $("select[name='region']").val();
        var orderStatus = $("select[name='orderStatus']").val();
        var driverPhone = $("input[name='driverPhone']").val();
        var str = "orderStatus="+orderStatus +"&driverPhone="+driverPhone+"&regionId="+regionId;
        window.location.href = "/orders/export1?"+str;
    });

    $table.bootstrapTable({
        url:"/orders/search",
        method:'post',
        contentType:"application/x-www-form-urlencoded",
        sidePagination:"server",
        pageSize:currentSize,
        pageList:[currentSize,20,50],
        pagination:true,
        striped:true,
        paginationPreText:"上一页",
        paginationNextText:"下一页",
        onPageChange:function (num,size) {
               currentPage = num;
               currentSize = size;
        },
        height:788,
        queryParams:function (params) {
            var regionId = $("select[name='region']").val();
            var orderStatus = $("select[name='orderStatus']").val();
            var driverPhone = $("input[name='driverPhone']").val();
            params.regionId= regionId;
            params.orderStatus= orderStatus;
            params.driverPhone= driverPhone;
            return params;
        },
        columns:[
            {
                title:"序号",
                formatter:function (value,row,index) {
                    return currentSize*(currentPage-1)+index+1;
                },
            }, {
                field:"sn",
                title:"工单编号",
                width:150,
            },{
                field:"createTime",
                title:"工单创建时间",
                width:170,
            },{
                field:"serviceRegion",
                title:"故障区域"
            },{
                field:"plateNum",
                title:"故障车车牌号",
                width:150
            },{
                field:"driverName",
                title:"故障车司机"
            },{
                field:"driverPhone",
                title:"故障车司机电话"
            },{
                field:"servicePoint.storeName",
                title:"名称/车牌",
                width:150
            },{
                field:"servicePoint.storeContact",
                title:"服务人",
                width:100,
            },{
                field:"totalCost",
                title:"报销费用",
                formatter:function (v,r,i) {
                    return v?v/100:"-";
                },
            },{
                field:"customerService.name",
                title:"客服"
            },{
                field:"orderSource",
                title:"工单来源",
                formatter:function(v,r,i){
                    if(v=="YUEYUN"){
                        return '<span >粤运</span>';
                    }
                    if(v=="ZHONGCE"){
                        return '<span >中策</span>';
                    }

                }
            },{
                field:"orderStatus",
                title:"工单状态",
                formatter:function(v,r,i){
                    if(v=="NOT_SERVICE"){
                        return '<span class=" fontColorGreen">待服务</span>';
                    }
                    if(v=="NOT_AUDIT"){
                        return '<span class=" fontColorPink">待审核</span>';
                    }
                    if(v=="COMPLETE"){
                        return '<span class=" fontColorBule_2">已完成</span>';
                    }
                    if(v=="CANCELED"){
                        return '<span class=" fontColorBule_2">已取消</span>';
                    }
                    if(v=="DENY"){
                        return '<span class=" fontColorPink">已拒绝</span>';
                    }

                }
            },{
                field:"",
                title:"操作",
                formatter:function (v,r,i) {
                    if(r.orderStatus=='NOT_SERVICE'){
                        return '<span data-id="'+r.orderId+'" class="fontColorBule_1 order-cancel">取消</span>';
                    }
                }
            }
        ]
    });
    $(document).on("click",".order-cancel",function(){
        var orderId = $(this).data("id");
        var cancelOrder = layer.confirm("确定取消该订单吗？",
            {icon: 3, title: "提示", fixed: true, move: false, offset: "40%"}
            , function () {
                $.ajax({
                    type: "put",
                    url: "/orders",
                    data: {
                        orderId: orderId,
                        orderStatus: "CANCELED",
                    },
                    success: function (result) {
                        refreshTable();
                        layer.close(cancelOrder);
                    }
                });
            }, function () {
                layer.close(cancelOrder);
            });
    });

});
function refreshTable() {
    $table.bootstrapTable("refresh");
}
function getData() {
    var regionId = $("select[name='region']").val();
    var orderStatus = $("select[name='orderStatus']").val();
    var driverPhone = $("input[name='driverPhone']").val();
    var json = {
        regionId:regionId,
        orderStatus:orderStatus,
        driverPhone:driverPhone,
    };

//		var str = "orderStatus="+orderStatus +"&driverPhone="+driverPhone+"&regionId="+regionId;
    return json;
}