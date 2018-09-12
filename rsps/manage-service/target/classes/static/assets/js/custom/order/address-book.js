$(function(){

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    // 点击新增
    $(".btn-address-add").click(function () {
        var addressType = $(this).attr("addressType");
        window.location.href = "/custom/order/showAddressAdd?addressType=" + addressType;
    });

    // 选择寄件地址
    $(".address-div").click(function () {
        var addressId = $(this).attr("addressId");
        var addressType = $(this).attr("addressType");
        $.ajax({
            url:"/custom/order/selectAddressBook",
            data:"addressId=" + addressId + "&addressType=" + addressType,
            success:function (msg) {
                window.location.href = "/custom/order/showOrder";
            }
        });
    });

    // 默认地址选择
    $(".default-select").click(function () {
        var checkObj = $(this).find("input[name='defaultSelect']");
        var addressId = $(this).attr("addressId");
        if (!$(checkObj).prop("checked")) {
            $.ajax({
                url:"/custom/order/handleAddressDefault",
                data:"addressId=" + addressId,
                success:function (msg) {
                    if (msg == "success") {
                        toastr.success("操作成功");
                        $(".default-address").prop("checked",false);
                        $(checkObj).prop("checked",true);
                    }
                }
            })

        }
    });

    // 删除
    $(".address-delete").click(function () {

        var addressId = $(this).attr("addressId");
        // var addressType = $(this).attr("addressType");
        var curObj = $(this);

        $.confirm({
            title: '警告',
            icon: 'fa fa-warning',
            content: '确定删除该地址簿吗？',
            type: 'red',
            typeAnimated: true,
            animation: 'rotateYR',
            closeAnimation: 'rotateYR',
            animateFromElement: false,
            buttons: {
                tryAgain: {
                    text: '确定',
                    // btnClass: 'btn-red',
                    action: function(){
                        $.ajax({
                            url:"/custom/order/address/delete",
                            data:"addressId=" + addressId,
                            success:function (msg) {

                                if (msg == "success") {
                                    toastr.success("删除成功");
                                    setTimeout(function () {
                                        curObj.parent().parent().parent().parent().remove();
                                    }, 1000);
                                    // window.location.href = "/custom/order/showAddressBook?addressType=" + addressType;
                                }

                            }
                        });
                    }
                },
                close: {
                    text:'取消'
                }
            }
        });


    });


});