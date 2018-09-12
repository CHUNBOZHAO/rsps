$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    // 创建订单
    $(".btn-order-create").click(function () {
        var senderInfo = $("input[name='senderInfo']").val();
        var receiverInfo = $("input[name='receiverInfo']").val();

        if (senderInfo == "") {
            toastr.warning("请完善寄件人信息");
            return;
        }

        if (receiverInfo == "") {
            toastr.warning("请完善收件人信息");
            return;
        }

        if (!$("#boxNotSelect").prop("checked")) {
            if ($(".box-selected-div").size() == 0) {
                toastr.warning("请选择智能包装箱");
                return;
            }
        }

        var myModal = $("#order_process_modal").modal({
            backdrop:'static',
            keyboard:false
        });

        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();

        var count = 1;
        createOrderStrategy(longitude, latitude, myModal, count);
    });
    
    function createOrderStrategy(longitude, latitude, myModal, count) {
        if (count < 9 && (longitude == "" || latitude == "")) {
            setTimeout(function () {
                var longitude = $("#longitude").val();
                var latitude = $("#latitude").val();
                createOrderStrategy(longitude, latitude, myModal, count + 1);
            }, 2000);
        } else {
            myModal.modal("hide");
            createOrder(longitude, latitude);
        }
    }

    // 生成订单
    function createOrder(longitude, latitude) {
        $.ajax({
            url:"/custom/order/create",
            data:"longitude=" + longitude + "&latitude=" + latitude,
            dataType:"json",
            success:function (msg) {
                if (msg.result == "save_success") {
                    toastr.success("下单成功");

                    $.confirm({
                        title: '提示',
                        content: '需要继续下单吗？',
                        icon: 'fa fa-info',
                        // type: 'orange',
                        typeAnimated: true,
                        animation: 'rotateYR',
                        closeAnimation: 'rotateYR',
                        animateFromElement: false,
                        buttons: {
                            tryAgain: {
                                text: '确定',
                                // btnClass: 'btn-red',
                                action: function() {
                                    window.location.href = "/custom/order/showOrder";
                                }
                            },
                            close: {
                                text:'取消',
                                action: function () {
                                    window.location.href = "/custom/";
                                }
                            }
                        }
                    });
                } else if (msg.result == "update_success") {
                    toastr.success("修改成功");
                    setTimeout(function () {
                        window.location.href = "/custom/";
                    }, 1000)
                } else if (msg.result == "failed") {
                    if (msg.reason == "state_error") {
                        toastr.warning("订单已经生效，修改失败");
                        setTimeout(function () {
                            window.location.href = "/custom/";
                        }, 1000)
                    } else {
                        toastr.warning("操作失败");
                    }
                } else {
                    toastr.warning("操作失败");
                }
            }
        });
    }

    // 地址簿点击事件
    $(".address-book-span").click(function () {
        var type = $(this).attr("type");
        window.location.href = "/custom/order/showAddressBook?addressType=" + type;
    });

    // 选择包装箱
    $("#box_select").click(function () {
        window.location.href = "/custom/order/showBoxType";
    });

    
    // 寄件人输入框点击
    $("#senderInfo").focus(function () {
        window.location.href = "/custom/order/showInputAddress?addressType=1";
    });

    // 收件人输入框点击
    $("#receiverInfo").focus(function () {
        window.location.href = "/custom/order/showInputAddress?addressType=2";
    });

    // 不需要包装箱
    $("#box-no-div").click(function () {
        $.ajax({
            url:"/custom/order/clearBoxes",
            success:function (msg) {
                if (msg == "success") {
                    $(".box-selected-div").remove();
                } else {
                    $(".box-selected-div").remove();
                }
            }            
        });
    });


    // 新增用户表单验证及提交
    // $("#order_create_form").validate({
    //     rules: {
    //         senderName: {
    //             required:true,
    //         },
    //         senderTel: {
    //             required: true,
    //             digits:true
    //         },
    //         senderArea1:{
    //             required: true,
    //         },
    //         senderArea2:{
    //             required: true,
    //         },
    //         senderArea3:{
    //             required: true,
    //         },
    //         senderAddress:{
    //             required: true,
    //         },
    //         receiverName:{
    //             required: true,
    //         },
    //         receiverTel:{
    //             required: true,
    //             digits:true
    //         },
    //         receiverArea1:{
    //             required: true,
    //         },
    //         receiverArea2:{
    //             required: true,
    //         },
    //         receiverArea3:{
    //             required: true,
    //         },
    //         receiverAddress:{
    //             required: true,
    //         }
    //     },
    //     messages:{
    //         senderName: {
    //             required:"寄件人姓名不能为空",
    //         },
    //         senderTel: {
    //             required: "手机号码不能为空",
    //             minlength:"手机号格式不正确",
    //             maxlength:"手机号格式不正确"
    //         },
    //         senderAddress:{
    //             required: "寄件人详细地址不能为空",
    //         },
    //         receiverName:{
    //             required: "收件人姓名不能为空",
    //         },
    //         receiverTel:{
    //             required: "手机号码不能为空",
    //             minlength:"手机号格式不正确",
    //             maxlength:"手机号格式不正确"
    //         },
    //         receiverAddress:{
    //             required: "收件人详细地址不能为空",
    //         }
    //     },
    //     errorElement: "em",
    //     errorPlacement: function ( error, element ) {
    //         // Add the `help-block` class to the error element
    //         error.addClass( "help-block" );
    //
    //         if ( element.prop( "type" ) === "checkbox" ) {
    //             error.insertAfter( element.parent( "label" ) );
    //         } else {
    //             error.insertAfter( element );
    //         }
    //     },
    //     highlight: function ( element, errorClass, validClass ) {
    //         $( element ).parents( ".input-parent" ).addClass( "has-error" ).removeClass( "has-success" );
    //     },
    //     unhighlight: function (element, errorClass, validClass) {
    //         $( element ).parents( ".input-parent" ).addClass( "has-success" ).removeClass( "has-error" );
    //     },
    //     submitHandler: function(form) {
    //         $(form).ajaxSubmit({
    //             dataType:"json",
    //             success:function (msg) {
    //                 if (msg.result == "success") {
    //                     toastr.success("下单成功");
    //                     $("#toastr-container").css("margin-top", "80px");
    //                     $(form).clearForm();
    //
    //                     // 定时跳转到我的订单
    //                     setTimeout(function() {
    //                         window.location.href = "/custom/";
    //                     }, 3000);
    //                 } else {
    //                     if (msg.msgCode == 600002) {
    //                         toastr.error("下单失败,暂时无人接单");
    //                     } else {
    //                         toastr.error("下单失败");
    //                     }
    //                 }
    //             }
    //         });
    //     }
    // });

});
