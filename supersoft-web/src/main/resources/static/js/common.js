/**
 * Created by d on 2016/12/12.
 */
var js=document.scripts;
var url=js[js.length-1].src;
var global=new Object();
global.context=getContex(url);

function getContex(url){
    var reg = new RegExp("(\\?|&)"+ 'ctx' +"=([^&]*)(&|$)");
    var r = url.substr(1).match(reg);
    if(r!=null)return unescape(r[2]);
    return null;
}