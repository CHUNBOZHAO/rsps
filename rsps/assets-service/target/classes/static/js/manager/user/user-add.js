$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 新增用户表单验证及提交
    $("#user_edit_form").validate({
        rules: {
            userName: {
                required:true,
            },
            realName: {
                required: true,
            },
            tel:{
                required: true,
                digits: true,
                minlength:11
            },
            userType:{
                required: true
            },
            age:{
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
                            window.location.href = "/manager/user/";
                        }, 2000);

                    } else {
                        if (msg.msgCode == 101) {
                            toastr.warning("该用户名已经存在");
                            $("#user_name").focus();
                        }else if(msg.msgCode == 102){
                            toastr.warning("用户编号已存在");
                            $("#operator_no").focus();
                        } else {
                            toastr.error("保存失败，请联系管理员")
                        }
                    }
                }
            });
        }
    });

        var entCode = $("#user_real_name").attr("entCode");
        var flag = true;
        $('#user_real_name').on('compositionstart',function(){
            flag = false;
        })
        $('#user_real_name').on('compositionend',function(){
            flag = true;
        })
        $('#user_real_name').on('input',function(){
            var _this = this;
            setTimeout(function(){

                if(flag){//TODO:添加事件

                    document.getElementById("user_name").value=entCode+'_'+$(_this).val()
                }
            },0)
        })


});
