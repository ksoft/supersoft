$(function () {
    var URL = {
        load : staticPath + "/web/showNews",
        dz : staticPath + "/webDzOpt"
    };
    var currentPage = 1;
    var isLoading = false;
    var tyreInfo = {
        loadShows : function (){
            var _self = this;
            var data = {
                "currentPage": currentPage,
                "pageSize": 10
            };
            $.ajax({
                url: URL.load,
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (msg) {
                    layer.closeAll('loading');
                    var container = $(".djsBox");//.empty();
                    if(msg.success){
                        var showList = msg.data.list;
                        if(showList.length>0){
                            for(var i=0; i<showList.length; i++){
                                var index = 0;
                                $("#showTemplate").tmpl(showList[i],{
                                    getIndex: function () {
                                        return index++;
                                    }
                                }).appendTo(container);
                            }
                        }
                    }
                    if(msg.data.totalPages <= currentPage){
                        $(window).unbind("scroll");
                        $(".djsBox").append("<li class='djsBoxLi' style='color:#999;font-size:0.22rem;text-align: center;'>——别再往下扯了——</li>");
                    }else{
                        currentPage++;

                        //点赞事件
                        $('.djsBoxDivRightDiv').unbind("click").click(function(){
                            var id = $(this).parent().parent().attr("id");
                            var isDz = $(this).find('h4').hasClass('djsBoxDivRightColor');
                            var dzNum = 0;
                            if(isDz){
                                dzNum = _self.dzOpt(id,-1);
                                $(this).find('h4').removeClass('djsBoxDivRightColor');
                            }else{
                                dzNum = _self.dzOpt(id,1);
                                $(this).find('h4').addClass('djsBoxDivRightColor');
                            }
                            $(this).find('b').toggleClass('djsBoxDivRightB');
                            if(dzNum > 0){
                                $(this).find("span").html(dzNum);
                            }
                        });
                    }
                    isLoading = false;
                }
            });
        },
        adjustImgSize : function(img, boxWidth, boxHeight) {
            img = $(img);
            // var imgWidth=img.width();
            // var imgHeight=img.height();
            // 上面这种取得img的宽度和高度的做法有点儿bug
            // src如果由两个大小不一样的图片相互替换时，上面的写法就有问题了，换过之后的图片的宽度和高度始终取得的还是换之前图片的值。
            // 解决方法：创建一个新的图片类，并将其src属性设上。
            var tempImg = new Image();
            tempImg.src = img.attr('src');
            var imgWidth=tempImg.width;
            var imgHeight=tempImg.height;

            //比较imgBox的长宽比与img的长宽比大小
            if((boxWidth/boxHeight)>=(imgWidth/imgHeight)){
                //重新设置img的width和height
                img.width((boxHeight*imgWidth)/imgHeight);
                img.height(boxHeight);
                //让图片居中显示
                var margin=(boxWidth-img.width())/2;
                img.css("margin-left",margin);
            }else{
                //重新设置img的width和height
                img.width(boxWidth);
                img.height((boxWidth*imgHeight)/imgWidth);
                //让图片居中显示
                var margin=(boxHeight-img.height())/2;
                img.css("margin-top",margin);
            }
        },
        loadImg : function(img,num){
            var _self = this;
            if(num%3 == 0){
                _self.adjustImgSize(img,107.66,107.66);
            }else if(num%2 == 0){
                _self.adjustImgSize(img,161.49,161.49);
            }else{
                _self.adjustImgSize(img,323,323);
            }
        },
        dzOpt : function(id,flag){
            layer.load(2, {
                shade: [0.1,'#555'],
                time:100000
            });
            var data = {
                "id": id,
                "flag": flag
            };
            var num = 0;
            $.ajax({
                url: URL.dz,
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                async: false,
                contentType: "application/json",
                success: function (msg) {
                    if(msg.success){
                        num = msg.data;
                    }
                    layer.closeAll('loading');
                }
            });

            return num;
        },
        loadView: function (img) {
            this.adjustImgSize(img,window.width,window.height)
        },
        viewImg : function(img,slide){
            img = $(img);
            var imgList = [];
            var i = 0;
            img.parent().parent().find("img").each(function () {
                imgList[i] = $(this).attr("src");
                i++;
            });

            $(".swiper-container").remove();
            $("#swiperTemplate").tmpl({'photos':imgList}).appendTo($("body"));

            var showSwiper = new Swiper('.swiper-container', {
                pagination : '.swiper-pagination',
                paginationType : 'bullets',
                autoHeight: true, //高度随内容变化
                width : window.innerWidth,
                initialSlide: slide,
            });
            layer.open({
             type: 1,
             shade: true,
             title: false, //不显示标题
             content: $('.swiper-container'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
             cancel: function(){

             }
             });
        },
        bindEvent : function () {
            var _self = this;

            $(window).unbind("scroll").bind("scroll", function(e){
                if ($(document).scrollTop()>=$(document).height()-$(window).height()) {
                    if(!isLoading){
                        isLoading = true;
                        layer.load(2, {
                            shade: [0.1,'#555'],
                            time:100000
                        });
                        window.setTimeout(function(){
                            _self.loadShows();
                        },1000);

                    }
                }
            });

        },
        init : function(){
            var _self = this;
            _self.bindEvent();
            _self.loadShows();
        }
    };
    window.TyreInfoObj = tyreInfo;
    tyreInfo.init();
});
