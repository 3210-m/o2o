$(function(){
    var addpcUrl = '/o2o/shopadmin/addproductcategory';


    $('#submit').click(function () {
        var productcategory = {};
        productcategory.productCategoryName = $('#product_category_name').val();
        productcategory.priority = $('#priority').val();
        productcategory.productCategoryDesc = $('#product_category_desc').val();

        var formData = new FormData();
        formData.append('productCategoryStr', JSON.stringify(productcategory));
        $.ajax({
            url: addpcUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            async:false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！ ' + data.errMsg);
                }
            }
        })
    })
})