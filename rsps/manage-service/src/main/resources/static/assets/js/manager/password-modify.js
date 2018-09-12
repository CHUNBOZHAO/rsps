$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 新增用户表单验证及提交
    $("#password_modify_form").validate({
        rules: {
            oldPassword: {
                required:true,
            },
            newPassword: {
                required: true,
            },
            newPassword2:{
                required: true,
                equalTo:"#new_password",
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
            $( element ).parents( ".col-sm-10" ).addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parents( ".col-sm-10" ).addClass( "has-success" ).removeClass( "has-error" );
        },
        submitHandler: function(form) {
            $(form).ajaxSubmit({
                dataType:"json",
                success:function (msg) {
                    if (msg.result == "success") {
                        toastr.success("密码修改成功")
                        $("#toastr-container").css("margin-top", "80px");
                        $(form).clearForm();
                    } else {
                        if (msg.msgCode == 200003) {
                            toastr.warning("原密码不正确");
                            $("#toastr-container").css("margin-top", "80px");
                            $("#old_password").focus();
                        } else {
                            toastr.error("密码修改失败")
                        }
                    }
                }
            });
        }
    });


});
