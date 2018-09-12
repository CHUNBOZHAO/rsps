$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    var itemsPerPage = 10;


    // // 初始化页面数据
    // function initPageData() {
    //     $.ajax({
    //         url:"/custom/order/boxes/current",
    //         data:"page=1&itemsPerPage=" + itemsPerPage,
    //         dataType:"html",
    //         success:function (content) {
    //             $("#current").html(content);
    //         }
    //     });
    // }
    //
    // initPageData();

    // 点击当前订单
    $("#index_order_current").click(function () {
        $.ajax({
            url:"/custom/order/boxes/current",
            data:"page=1&itemsPerPage=" + itemsPerPage,
            dataType:"html",
            success:function (content) {
                $("#current").html(content);
            }
        });
    });

    // 点击获取更多
    $(document).on("click",".next-page-current-data",function () {
        var nextPage = parseInt($(this).attr("curPage")) + 1;
        var obj = $(this);
        $.ajax({
            url:"/custom/order/boxes/current",
            data:"page=" + nextPage + "&itemsPerPage=" + itemsPerPage,
            dataType:"html",
            success:function (content) {
                obj.replaceWith(content);
            }
        });
    });


    // 点击历史订单
    $("#index_order_history").click(function () {
        $.ajax({
            url:"/custom/order/boxes/history",
            data:"page=1&itemsPerPage=" + itemsPerPage,
            dataType:"html",
            success:function (content) {
                $("#history").html(content);
            }
        });
    });

    // 点击获取更多
    $(document).on("click",".next-page-history-data",function () {
        var nextPage = parseInt($(this).attr("curPage")) + 1;
        var obj = $(this);
        $.ajax({
            url:"/custom/order/boxes/history",
            data:"page=" + nextPage + "&itemsPerPage=" + itemsPerPage,
            dataType:"html",
            success:function (content) {
                obj.replaceWith(content);
            }
        });
    });

    // 点击我的包装箱
    $(document).on("click","#index_order_my_box", function () {
        $.ajax({
            url:"/custom/order/queryMyBoxes",
            dataType:"html",
            success:function (content) {
                $("#myBox").html(content);
            }
        });

        // 更新我的包装箱数量
        $.ajax({
            url:"/custom/order/myBoxes/count/query",
            success:function (msg) {
                $("#index_my_box_count").show();
                $("#index_my_box_count").text(msg);
            }
        })
    })


    // $("#index_order_my_box").click(function () {
    //
    // });

    // 点击查询输入框
    $("#index_input_order_query").on("click", function () {
        window.location.href = "/custom/order/query"
    });

    // 当前订单点击详情
    $(document).on("click", ".current-order", function () {
        var boxId = $(this).attr("boxId");
        var orderId = $(this).attr("orderId");
        var orderType = $(this).attr("orderType");
        var orderState = $(this).attr("orderStatus");
        var orderSource = $(this).attr("orderSource");  // 1 C 2 B
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();

        if (orderSource == 1 && (orderState == 0 || orderState == 1)) {  // "等待接单"、"接单失败"点击进行编辑
            window.location.href = "/custom/order/showOrder?orderId=" + orderId;
        } else {
            window.location.href = "/custom/order/box/detail?boxId=" + boxId + "&orderId=" + orderId + "&orderType=" + orderType + "&orderState=" + orderState + "&longitude=" + longitude + "&latitude=" + latitude;
        }
    });

    // 历史订单点击详情
    $(document).on("click", ".history-order", function () {
        var boxId = $(this).attr("boxId");
        var orderId = $(this).attr("orderId");
        var orderType = $(this).attr("orderType");
        window.location.href = "/custom/order/box/detail?boxId=" + boxId + "&orderId=" + orderId + "&orderType=" + orderType + "&orderState=5";
    });

    // 重新下发订单
    $(document).on("click", ".retry-order", function (e) {
        var orderId = $(this).attr("orderId");
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();

        var curObj = $(this);
        $.ajax({
            url:"/custom/order/retryOrder",
            data:"orderId=" + orderId + "&longitude=" + longitude + "&latitude=" + latitude,
            success:function (msg) {
                if (msg == "success") {
                    curObj.hide();
                    curObj.next().hide();
                    curObj.prev().text("等待接单");
                } else {
                    toastr.warning("重试失败");
                }
            }
        })
        e.stopPropagation();
    });

    // 删除订单
    $(document).on("click", ".delete-order", function (e) {
        var curObj = $(this);
        var orderId = $(this).attr("orderId");

        $.confirm({
            title: '提示',
            content: '确定删除该订单吗？',
            icon: 'fa fa-info',
            type: 'red',
            typeAnimated: true,
            animation: 'rotateYR',
            closeAnimation: 'rotateYR',
            animateFromElement: false,
            buttons: {
                tryAgain: {
                    text: '确定',
                    // btnClass: 'btn-red',
                    action: function() {
                        $.ajax({
                            url:"/custom/order/deleteOrder",
                            data:"orderId=" + orderId,
                            success:function (msg) {
                                if (msg == "success") {
                                    toastr.success("删除成功");
                                    curObj.parent().parent().parent().remove();
                                } else {
                                    toastr.success("删除失败");
                                }
                            }
                        })
                    }
                },
                close: {
                    text:'取消',
                }
            }
        });
        e.stopPropagation();
    })

    var pageIndex = $("#tab_content").attr("pageIndex");

    if (pageIndex == 1) {
        $("#index_order_current").click();
    } else if (pageIndex == 2) {
        $("#index_order_history").click();
    } else if (pageIndex == 3) {
        $("#index_order_my_box").click();
    }

});