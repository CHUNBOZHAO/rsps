$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    var lineId = $("#lineId").val();
    // 加载数据
    $.ajax({
        url:"/manager/line/related/custom/list",
        data:"lineId=" + lineId,
        dataType:"html",
        success:function (content) {
            $("#related_content").html(content);
        }
    });

    // 关联请求
    function relateAction(data, dataArray, myModal) {
        $.ajax({
            url:"/manager/line//custom/relate/do",
            data:data,
            dataType:"json",
            success:function (msg) {
                setTimeout(function () {
                    myModal.modal("hide");
                    if (msg.result == "success") {
                        toastr.success("关联成功");
                        handleResult(dataArray, true);
                    } else {
                        toastr.warning("关联失败");
                    }
                },1000)
            }
        });
    }

    // 取消关联请求
    function relateCancelAction(data, dataArray, myModal) {
        $.ajax({
            url:"/manager/line/custom/relate/cancel",
            data:data,
            dataType:"json",
            success:function (msg) {
                setTimeout(function () {
                    myModal.modal("hide");
                    if (msg.result == "success") {
                        toastr.success("取消关联成功");
                        handleResult(dataArray, false);
                    } else {
                        toastr.error("取消关联失败");
                    }
                }, 1000)
            }
        });
    }

    // 关联结果显示
    function handleResult(dataArray, result) {
        var content = "";
        if (result) {
            content = "<span style='color: #2eba2b'>已关联</span>"
        } else {
            content = "<span style='color: #9e9e9e'>未关联</span>"
        }
        for (var i = 0; i < dataArray.length; i++) {
            $("#related_flag_" + dataArray[i]).html(content);
            if (result) {
                $("#ck_item_" + dataArray[i]).removeClass("item-unrelated");
                $("#ck_item_" + dataArray[i]).addClass("item-related");
            } else {
                $("#ck_item_" + dataArray[i]).removeClass("item-related");
                $("#ck_item_" + dataArray[i]).addClass("item-unrelated");
            }
        }
        $("#ck_all").prop("checked", false);
        $(".ck-item").prop("checked", false);
    }

    // 批量关联
    $(document).on("click", "#btn_batch_relate", function () {
        var isChecked = $.isChecked();
        if (isChecked) {
            var dataArray = new Array();
            var params = $(".item-unrelated:checked").map(function () {
                var res = "customIds=" + $(this).val();
                dataArray.push($(this).val());
                return res;
            }).get().join("&");
            if (dataArray.length > 500) {
                toastr.warning("一次最多关联500项，请重新选择");
            } else {
                var lineId = $(this).attr("lineId");
                var data = "lineId=" + lineId + "&" + params;
                var myModal = $("#process_modal").modal({
                    backdrop:'static',
                    keyboard:false
                });
                relateAction(data, dataArray, myModal);
            }
        } else {
            toastr.warning("请选择需要关联项");
        }
    });

    // 批量取消关联
    $(document).on("click", "#btn_batch_cancel_relate", function () {
        var isChecked = $.isChecked();
        if (isChecked) {
            var dataArray = new Array();
            var params = $(".item-related:checked").map(function (t) {
                var res = "customIds=" + $(this).val();
                dataArray.push($(this).val());
                return res;
            }).get().join("&");
            if (dataArray.length > 500) {
                toastr.warning("一次最多取消关联500项，请重新选择");
            } else {
                var lineId = $(this).attr("lineId");
                var data = "lineId=" + lineId + "&" + params;
                var myModal = $("#process_modal").modal({
                    backdrop:'static',
                    keyboard:false
                });
                relateCancelAction(data, dataArray, myModal);
            }
        } else {
            toastr.warning("请选择需要取消关联项");
        }
    });

    // 单个关联
    $(document).on("click", ".btn-relate", function () {
        var lineId = $(this).attr("lineId");
        var customId = $(this).attr("customId");
        var data = "lineId=" + lineId + "&customIds=" + customId;
        var dataArray = new Array();
        dataArray.push(customId);
        var myModal = $("#process_modal").modal({
            backdrop:'static',
            keyboard:false
        });
        relateAction(data, dataArray, myModal);
    });

    // 单个取消关联
    $(document).on("click", ".btn-relate-cancel", function () {
        var lineId = $(this).attr("lineId");
        var customId = $(this).attr("customId");
        var data = "lineId=" + lineId + "&customIds=" + customId;
        var dataArray = new Array();
        dataArray.push(customId);
        var myModal = $("#process_modal").modal({
            backdrop:'static',
            keyboard:false
        });
        relateCancelAction(data, dataArray, myModal);
    });

});