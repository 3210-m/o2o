$(function () {
    var shopId = getQueryString("shopId");
    var listUrl = '/o2o/shopadmin/productcategory?shopId=' + shopId;
    var addUrl = '/o2o/shopadmin/addproductcategory';
    var deleteUrl = '/o2o/shopadmin/deleteproductcategory';

    // $.getJSON(listUrl, function (data) {
    //     if (data.success) {
    //         var dataList = data.productCategoryList;
    //         $('.category-wrap').html('');
    //         var tempHtml = '';
    //         dataList.map(function (item, index) {
    //             tempHtml += ''
    //                 + '<div class="row row-product-category now">'
    //                 + '<div class="col-33 product-category-name">'
    //                 + item.productCategoryName
    //                 + '</div>'
    //                 + '<div class="col-33">'
    //                 + item.priority
    //                 + '</div>'
    //                 + '<div class="col-33"><a href="#" class="button delete" data-id="'
    //                 + item.productCategoryId
    //                 + '">删除</a></div>' + '</div>';
    //         });
    //         $('.category-wrap').append(tempHtml);
    //     }
    // });

    function getList() {
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var dataList = data.productCategoryList;
                $('.category-wrap').html('');
                var tempHtml = '';
                dataList.map(function (item, index) {
                    tempHtml += ''
                        + '<div class="row row-product-category now">'
                        + '<div class="col-33 product-category-name">'
                        + item.productCategoryName
                        + '</div>'
                        + '<div class="col-33">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-33"><a href="#" class="button delete" data-id="'
                        + item.productCategoryId
                        + '">删除</a></div>'
                        + '</div>';
                });
                $('.category-wrap').append(tempHtml);
            }
        });
    }

    getList();


    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var target = e.currentTarget;
            $.confirm('确定么?', function () {
                $.ajax({
                    url: deleteUrl,
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id,
                        shopId: shopId
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.toast('删除成功！');
                            getList();
                        } else {
                            $.toast('删除失败！');
                        }
                    }
                });
            });
        });

    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();

        });
});