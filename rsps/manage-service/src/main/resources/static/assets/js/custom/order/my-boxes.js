$(function(){

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    // 选择包装箱
    $(document).on("click",".my-box",function () {
        var recycleType = $(this).attr("recycleType");
        if (recycleType == 0) {  // 已经申请回收的包装箱不做处理
            return;
        }
        var selectObj = $(this).find("div[class='my-box-select-flag']");
        if ($(this).hasClass("my-box-style")) {
            $(this).removeClass("my-box-style");
            selectObj.hide();
        } else {
            $(this).addClass("my-box-style");
            selectObj.show();
        }
    });

    // 点击还包装事件处理
    $(document).on("click", ".btn-select-return-box", function () {
        var boxIds = "";
        $(".my-box-style").each(function (i, n) {
            var boxId = $(this).attr("boxId");
            var boxParam = "";
            if (i == 0) {
                boxParam = "?boxIds=" + boxId;
            } else {
                boxParam = "&boxIds=" + boxId;
            }
            boxIds += boxParam;
        });
        if (boxIds == "") {
            toastr.warning("请选择需要归还的包装箱");
        } else {
            window.location.href = "/custom/order/showBoxReturn" + boxIds;
        }
    });


});