


//验证码
function changeVerifyCode(img) {
    img.src = "../Kaptcha?"+Math.floor(Math.random()*100);
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
    var re = window.location.search.substr(1);
    var r = re.match(reg);
    if(r!=null){
        return decodeURIComponent(r[2]);
    }
    return '';
}