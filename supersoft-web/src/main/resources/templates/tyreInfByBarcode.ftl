<!DOCTYPE html>
<html xmlns:th=http://www.thymeleaf.org></html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="HandheldFriendly" content="true">
    <title></title>
    <link type="text/css" href="${staticPath}/static/css/tyreInfoByBarcode.css" rel="stylesheet"/>
    <script src="${staticPath}/static/vendor/jquery/jquery.js"></script>
    <script src="${staticPath}/static/vendor/jquery-tmpl/jquery.tmpl.min.js"></script>
    <script src="${staticPath}/static/vendor/layer/layer.js"></script>
    <link type="text/css" href="${staticPath}/static/vendor/swiper/swiper-3.4.2.min.css" rel="stylesheet"/>
    <script src="${staticPath}/static/vendor/swiper/swiper-3.4.2.jquery.min.js"></script>
    <script>
        var staticPath= '${staticPath}';
    </script>
</head>
<body>
    <div class="top">
        <h1 class="topLogo"><img src="${staticPath}/static/img/logo.png"></h1>
    </div>
    <div class="middle">
        <div class="middleD">
            <p>
                <span>轮胎花纹</span>
                <b>SA37</b>
            </p>
            <p>
                <span>轮胎尺寸</span>
                <b>215/55R17</b>
            </p>
            <p>
                <span>速度级别</span>
                <b>W</b>
            </p>
            <div class="middlePosition">
                <h6>¥599.00</h6>
                <label>全国建议零售价</label>
            </div>
        </div>
        <img src="../static/img/d1.png">
        <img src="../static/img/d2.png">
        <img src="../static/img/d3.png" style="">
    </div>
    <div class="dajiashuo">
        <div class="djsTitle">
            <span></span>
            <b>大家说</b>
        </div>
        <ul class="djsBox">
            <#--<li class="djsBoxLi">
                <div class="djsBoxDiv">
                    <div class="djsBoxDivLeft"><img src="../static/img/pic_1.png"></div>
                </div>
                <div class="djsBoxDivRight">
                    <b>杭州李云轮胎店</b>
                    <p>不愧是国产神胎， 性价比超高，配我的
                        奔驰非常不错，本来车子有点跑舵，换
                        上新胎竟然解决了！ 比原车的邓禄普强
                        太多了！会大力推荐给朋友！五分好评！
                    </p>
                    <label>#朝阳CHAOYANG 185/60R15 [RP58]  兰博基尼-Huracan#</label>
                    <ol>
                        <li><img src="../static/img/pic_2.png"></li>
                        <li><img src="../static/img/pic_3.png"></li>
                        <li><img src="../static/img/pic_2.png"></li>

                        <li><img src="../static/img/pic_2.png"></li>
                        <li><img src="../static/img/pic_2.png"></li>
                        <li><img src="../static/img/pic_2.png"></li>

                        <li><img src="../static/img/pic_2.png"></li>
                        <li><img src="../static/img/pic_2.png"></li>
                        <li><img src="../static/img/pic_2.png"></li>
                    </ol>
                    <div class="djsBoxDivRightDiv">
                        <h5>2017-03-21</h5>
                        <h4><b></b>&nbsp;123</h4>
                    </div>
                </div>
            </li>-->
        </ul>
        <script  id="showTemplate" type="text/x-jquery-tmpl">
            <li class="djsBoxLi" id="{{= id}}">
                <div class="djsBoxDiv">
                    <div class="djsBoxDivLeft"><img src="../static/img/pic_1.png"></div>
                </div>
                <div class="djsBoxDivRight">
                    <b>{{= storeName}}</b>
                    <p>{{= content}}</p>
                    <label># {{= gg}}  {{= carName}} #</label>
                    <ol>
                    {{each photos}}
                    <li><img src="{{= $value}}"  onload="TyreInfoObj.loadImg(this,{{= photos.length}})" onclick="TyreInfoObj.viewImg(this,{{= $item.getIndex()}})"></li>
                    {{/each}}
                    </ol>
                    <div class="djsBoxDivRightDiv">
                        <h5>{{= modifyTm}}</h5>
                        {{if hasUserPlused}}
                        <h4 class="djsBoxDivRightColor"><b class="djsBoxDivRightB"></b>&nbsp;<span>{{= dz}}</span></h4>
                        {{else}}
                        <h4><b></b>&nbsp;<span>{{= dz}}</span></h4>
                        {{/if}}
                    </div>
                </div>
            </li>
        </script>
    </div>
    <script  id="swiperTemplate" type="text/x-jquery-tmpl">
        <div class="swiper-container" style="width:100%;height: 100%">
            <div class="swiper-wrapper">
            {{each photos}}
                <div class="swiper-slide"><img src="{{= $value}}" onload="TyreInfoObj.loadView(this)"></div>
            {{/each}}
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </script>
    <script type="text/javascript">
        (function (doc, win) {
            var docEl = doc.documentElement,
                    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                    recalc = function () {
                        var clientWidth = docEl.clientWidth;
                        if (!clientWidth) return;
                        docEl.style.fontSize = 100 * (clientWidth / 720) + 'px';
                    };

            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);

    </script>

    <script src="${staticPath}/static/js/tyreInfoByBarcode.js"></script>

</body>
</html>