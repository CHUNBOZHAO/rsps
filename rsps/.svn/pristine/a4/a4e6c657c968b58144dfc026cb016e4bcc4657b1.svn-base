$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 新增用户表单验证及提交
    $("#enterprise_form").validate({
        rules: {
            userName: {
                required:true,
            },
            nickName: {
                required:true,
            },
            entName: {
                required: true,
            },
            entAddress:{
                required: true,
            },
            entCode: {
                required: true,
            },
            email: {
                email:true,
            },
            tel: {
                digits: true
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
                        toastr.success("保存成功");
                        setTimeout(function () {
                            window.location.href = "/manager/enterprise/list/show";
                        }, 2000);

                    } else {
                        if (msg.msgCode == 101) {
                            toastr.warning("用户名已经存在");
                            $("#user_name").focus();
                        }else if(msg.msgCode == 102) {
                            toastr.warning("企业名已经存在");
                            $("#ent_name").focus();
                        }else if(msg.msgCode == 103) {
                             toastr.warning("企业编码已经存在");
                             $("#ent_code").focus();
                        }else {
                            toastr.error("保存失败，请联系管理员")
                        }
                    }
                }
            });
        }
    });


});
