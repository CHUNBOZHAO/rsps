$(function(){

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    // 确定
    $(".btn-address-ok").click(function () {
        $("#btn_address_ok").click();
    });

    // 新增用户表单验证及提交
    var submit = true;
    $("#address_create_form").validate({
        rules: {
            name: {
                required:true,
            },
            tel: {
                required: true,
                digits:true
            },
            areaProvince:{
                required: true,
            },
            areaCity:{
                required: true,
            },
            areaCounty:{
                required: true,
            },
            detailAddress:{
                required: true,
            }
        },
        messages:{
            name: {
                required:"姓名不能为空",
            },
            tel: {
                required: "手机号码不能为空",
                minlength:"手机号码不正确",
                maxlength:"手机号码不正确"
            },
            areaProvince:{
                required: "不能为空",
            },
            areaCity:{
                required: "不能为空",
            },
            areaCounty:{
                required: "不能为空",
            },
            detailAddress:{
                required: "详细地址不能为空",
            },
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parents( ".input-parent" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".input-parent" ).addClass( "has-success" ).removeClass( "has-error" );
        },
        submitHandler: function(form) {
            if (!submit) {
                return;
            }
            $(form).ajaxSubmit({
                dataType:"json",
                success:function (msg) {
                    if (msg.result == "success") {
                        submit = false;
                        toastr.success("操作成功");

                        // 定时跳转到我的订单
                        var addressType = msg.addressType;
                        setTimeout(function() {
                            window.location.href = "/custom/order/showAddressBook?addressType=" + addressType;
                        }, 2000);
                    } else {
                        toastr.error("操作失败");
                    }
                }
            });
        }
    });


});