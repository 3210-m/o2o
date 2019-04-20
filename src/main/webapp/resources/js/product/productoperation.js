$(function () {
    var productId = getQueryString('productId');
    var infoUrl = '/o2o/productadmin/getproductbyid?productId=' + productId;
    var categoryUrl = '/o2o/productcategoryadmin/productcategory';
    var productPostUrl = '/o2o/productadmin/modifyproduct';

    var isEdit = false;
    if (productId) {
        getInfo(productId);
        isEdit = true;
    } else {
        getCategory();
        productPostUrl = '/o2o/productadmin/addproduct';
    }

    function getInfo(id) {
        $.getJSON(infoUrl, function (data) {
            if (data.success) {
                var product = data.product;
                $('#product_name').val(product.productName);
                $('#product_desc').val(product.productDesc);
                $('#priority').val(product.priority);
                $('#normal_price').val(product.normalPrice);
                $('#promotion_price').val(product.promotionPrice);

                var optionHtml = '';
                var optionArr = data.productCategoryList;
                var optionSelected = product.productCategory.productCategoryId;

                optionArr.map(function (item, index) {
                    var isSelect = optionSelected === item.productCategoryId ? 'selected' : '';
                    optionHtml += '<option data-value="'
                        + item.productCategoryId
                        + '"'
                        + isSelect
                        + '>'
                        + item.productCategoryName
                        + '</option>';
                });
                $('#product_category').html(optionHtml);
            }
        });
    }
    function getCategory() {
        $.getJSON(categoryUrl,function (data) {
            if(data.success){
                var productCategoryList = data.productCategoryList;
                var optionHtml = '';
                productCategoryList.map(function (item,index) {
                    optionHtml += '<option data-value="'
                        + item.productCategoryId
                        + '">'
                        + item.productCategoryName
                        + '</option>';
                });
                $('#product_category').html(optionHtml);
            }
        })
    }

    $('.detail_img_dev').on('change','.detail_img:last-child',function () {
        if($('.detail_img').length<6){
            $('#detail_img').append('<input type="file" class="detail_img">');
        }
    });

    //提交按钮：对商品的添加和编辑做不同的响应
    $('#submit').click(function () {
        //创建json对象并赋值
        var product = {};
        product.productName = $('#product_name').val();
        product.productDesc = $('#product_desc').val();
        product.priority = $('#priority').val();
        product.normalPrice = $('#normal_price').val();
        product.promotionPrice = $('#promotion_price').val();
        //获取选的商品类别
        product.productCategory = {productCategoryId:$('#product_category').find('option').not(function () {
                return !this.selected;
            }).data('value')
        };
        product.productId = productId;
        //获取缩略图的文件流
        var thumbnail = $('#small_img')[0].files[0];


        var formData = new FormData();
        formData.append('thumbnail',thumbnail);
        $('.detail_img').map(
            function(index, item) {
                if ($('.detail_img')[index].files.length > 0) {
                    formData.append('productImg' + index,
                        $('.detail_img')[index].files[0]);
                }
            });
        formData.append('productStr', JSON.stringify(product));
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);
        $.ajax({
            url : productPostUrl,
            type : 'POST',
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    $.toast('提交成功！');
                } else {
                    $.toast('提交失败！');
                }
                $('#captcha_img').click();
            }
        });
    });

})