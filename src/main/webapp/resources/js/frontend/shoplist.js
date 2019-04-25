$(function () {
    // 加载flag
    var loading = false;
    // 最多可加载的条目
    var maxItems = 999;

    var pageSize = 3;
    var listUrl = '/o2o/frontend/listshops';
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    var pageNum = 1;
    var parentId = getQueryString('parentId');
    var areaId = getQueryString('areaId');
    var shopCategoryId = '';
    var shopName = '';

    getSearchDivData();

    //预加载10条
    addItems(pageSize, pageNum);


    /**
     * 获取店铺和区域列表
     */
    function getSearchDivData() {
        var url = searchDivUrl + '?' + 'parentId=' + parentId;
        $.getJSON(url, function (data) {
            if (data.success) {
                var shopCategoryList = data.shopCategoryList;
                var html = '';
                html += '<a href = "#" class="button" id="parentId" data-category-id="">全部类别</a>'
                shopCategoryList.map(function (item, index) {
                    html += '<a href = "#" class="button"  data-category-id='
                        + item.shopCategoryId
                        + '>'
                        + item.shopCategoryName
                        + '</a>';
                });
                $('#shoplist-search-div').html(html);
                var selectOptions = '<option value="">全部街道</option>';
                var areaList = data.areaList;
                areaList.map(function (item, index) {
                    selectOptions += '<option value="'
                        + item.areaId
                        + '">'
                        + item.areaName
                        + '</option>'
                });

                $('#area-search').html(selectOptions);
            }
        });
    }

    /**
     * 分页店铺列表
     * @param pageSize
     * @param pageIndex
     */
    function addItems(pageSize, pageIndex) {
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize=' + pageSize + '&parentId='
            + parentId + '&areaId=' + areaId + '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;

        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                maxItems = data.count;
                var html = '';
                data.shopList.map(function (item, index) {
                    html += '' + '<div class="card" data-shop-id="'
                        + item.shopId + '">' + '<div class="card-header">'
                        + item.shopName + '</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.shopImg + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.shopDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                $('.list-div').append(html);

                //1.获取当前显示的卡片总数，如果达到上限 就停止加载
                var total = $('.list-div .card').length;
                if (total >= maxItems) {
                    // 加载完毕，则注销无限加载事件，以防不必要的加载
                    //$.detachInfiniteScroll($('.infinite-scroll'));
                    // 删除加载提示符
                    $('.infinite-scroll-preloader').remove();
                    parentId='';
                }

                //2.否则页码+1，继续load
                pageNum += 1;
                loading = total >= maxItems?true:false;
                //刷新以显示新加载的店铺
                $.refreshScroller();
            }
        });
    }

    $(document).on('infinite', '.infinite-scroll-bottom', function () {
        if (loading) return;

        addItems(pageSize, pageNum);
    });

    //点击卡片进入店铺详情页
    $('.shop-list').on('click', '.card', function (e) {
        var shopId = e.currentTarget.dataset.shopId;
        window.location.href = '/o2o/forntend/shopdetail?shopId=' + shopId;
    });

    //点击类别查询时，重置页码、loading，清空原有的店铺列表,

    $('#shoplist-search-div').on('click', '.button', function (e) {
        //parentId存在
        //parentId
        if (parentId) {
            shopCategoryId = e.target.dataset.categoryId;

            //已选过的变灰，当前选中变亮
            if ($(e.target).hasClass('button-fill')) {
                $(e.target).removeClass('button-fill');
                shopCategoryId = '';
            } else {
                $(e.target).addClass('button-fill').siblings().removeClass('button-fill');

            }

            $('.list-div').empty();
            pageNum = 1;
            addItems(pageSize, pageNum);
        } else {
            parentId = e.target.dataset.categoryId;
            if ($(e.target).hasClass('button-fill')) {
                $(e.target).removeClass('button-fill');
                parentId = '';
            } else {
                $(e.target).addClass('button-fill').siblings().removeClass('button-fill');
            }
            $('.list-div').empty();
            pageNum = 1;
            addItems(pageSize,pageNum);
            //parentId = '';
        }
    });

    $('#search').on('input',function (e) {
        shopName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    $('#area-search').on('change', function(e) {
        areaId = $('#area-search').val();
        parentId = document.querySelector('#shoplist-search-div > a.button.button-fill').getAttribute("data-category-id");
        //alert(parentId);


         console.log(parentId);
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    $('#me').click(function() {
        $.openPanel('#panel-right-demo');
    });

    $.init();
});


