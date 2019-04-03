/**
 *
 */
$(function () {
    getproductcategory();
});

function getproductcategory(e) {
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
            + item.productCategoryName + '</div><div class="col-40">'
            + item.priority
            + '</div><div class="col-20">'
            + '<a href="/o2o/productcategoryadmin/deleteproductcategory">删除</a>'
            + '<input type="hidden" id="productCategoryId" name="productCategoryId" value='
            + item.productCategoryId+' /></div></div>';
        //<a href="#?shopId=' + id + '">删除</a>
    });
    $('.productcategory-wrap').html(html);
}

