$(function () {

    // 默认获取焦点
    $("#order_query_input").focus();

    // 订单查询
    $("#btn_order_query").click(function () {
        var orderId = $("#order_query_input").val();
        if (orderId == "") {
            return;
        }

        $.ajax({
            url:"/custom/order/handleQuery",
            data:"orderId=" + orderId,
            dataType:"html",
            success:function (content) {
                $("#boxes_query_result").html(content);
            }
        });
    });

    // 当前订单点击详情
    $(document).on("click", ".current-order", function () {
        var boxId = $(this).attr("boxId");
        var orderId = $(this).attr("orderId");
        var orderType = $(this).attr("orderType");
        var orderState = $(this).attr("orderStatus");
        if (orderState == 0 || orderState == 1) {  // "等待接单"、"接单失败"点击进行编辑
            window.location.href = "/custom/order/showOrder?orderId=" + orderId;
        } else {
            window.location.href = "/custom/order/box/detail?boxId=" + boxId + "&orderId=" + orderId + "&orderType=" + orderType + "&orderState=" + orderState;
        }
    });

    // 当前订单点击详情
    $(document).on("click", ".history-order", function () {
        var boxId = $(this).attr("boxId");
        var orderId = $(this).attr("orderId");
        var orderType = $(this).attr("orderType");
        window.location.href = "/custom/order/box/detail?boxId=" + boxId + "&orderId=" + orderId + "&orderType=" + orderType + "&orderState=5";
    });

});