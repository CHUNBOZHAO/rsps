$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 新增用户表单验证及提交
    $("#register_form").validate({
        rules: {
            tel: {
                required:true,
                digits:true
            },
            password: {
                required: true,
            },
            password2:{
                required: true,
                equalTo:"#password",
            },
        },
        messages:{
            tel:{
                required:"手机号不能为空",
                minlength:"手机号格式不正确",
                maxlength:"手机号格式不正确",
            },
            password: {
                required:"密码不能为空",
                minlength:jQuery.validator.format("密码不能小于{0}个字符"),
                maxlength:jQuery.validator.format("密码不能大于{0}个字符"),
            },
            password2:{
                required:"确认密码不能为空",
                minlength:jQuery.validator.format("密码不能小于{0}个字符"),
                maxlength:jQuery.validator.format("密码不能大于{0}个字符"),
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
            $(form).ajaxSubmit({
                dataType:"json",
                success:function (msg) {
                    if (msg.result == "success") {
                        toastr.success("注册成功")
                        $("#toastr-container").css("margin-top", "80px");
                        $(form).clearForm();
                        setTimeout(function () {
                            window.location.href = "/custom/login/";
                        }, 3000)

                    } else {
                        toastr.error("注册失败,该账号可能被使用")
                    }
                }
            });
        }
    });


});
