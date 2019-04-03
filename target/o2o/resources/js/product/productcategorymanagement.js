/**
 *
 */
$(function () {

    function getproductcategory() {
        $.ajax({
            url: '/o2o/productcategoryadmin/productcategory',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    handlePCList(data.productCategoryList);
                }
            }
        });
    }

    function handlePCList(data) {
        var html = '';
        data.map(function (item, index) {
            html += '<div class="row row-shop"><div class="col-40">'
                + item.productCategoryName
                + '</div><div class="col-40">'
                + item.priority
                + '</div>'
                + '<div class="col-33"><a href="#" class="button delete" data-id="'
                + item.productCategoryId
                + '">删除</a></div>'
                + '</div>';
            //<a href="#?shopId=' + id + '">删除</a>
        });
        $('.productcategory-wrap').html(html);
    }
    getproductcategory();

});

