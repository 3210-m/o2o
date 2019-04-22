$(function () {
    var url = '/o2o/frontend/listmainpageinfo';
    $.getJSON(url, function (data) {
        if (data.success) {
            //获取头条
            var headLineList = data.headLineList;
            var swiperHtml = '';
            //遍历
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    + '<a href="'
                    + item.lineLink
                    + '"external> <img class="banner-img" src="'
                    + item.lineImg
                    + '"alt="'
                    + item.lineName
                    + '"></a>'
                    + '</div>';
            });
            $('.swiper-wrapper').html(swiperHtml);
            $('.swiper-container').swiper({
                //切换间隔
                autoplay: 1000,
                //用户对轮播图进行操作时，是否自动停止
                autoplayDisableOnInteraction: false
            });


            //获取一级列表
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    + '<div class="col-50 shop-classify" data-category='
                    + item.shopCategoryId
                    + '>'
                    + '<div class="word">'
                    + '<p class="shop-title">'
                    + item.shopCategoryName
                    + '</p>'
                    + '<p class="shop-desc">'
                    + item.shopCategoryDesc
                    + '</p>'
                    + '</div>'
                    + '<div class="shop-classify-img-wrap"> '
                    + '<img class="shop-img" src="'
                    + item.shopCategoryImg
                    + '">'
                    + '</div>'
                    + '</div>';

            });
            $('.row').html(categoryHtml);
        }
    });

    //点击"我"显示侧栏
    $('#me').click(function(){
       $.openPanel('#panel-right-demo');
    });

    $('.row').on('click','.shop-classify',function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/o2o/frontend/shoplist?parentId='+shopCategoryId;
        window.location.href=newUrl;
    });
})