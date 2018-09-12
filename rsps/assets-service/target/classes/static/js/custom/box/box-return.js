$(function(){

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    };

    // 确定
    $(document).on("click", ".btn-box-return-ok", function () {
        var boxId = $(this).attr("boxId");
        $.ajax({
            url:"/custom/box/recycle/apply",
            data:"boxId=" + boxId,
            success:function (msg) {
                if (msg == "success") {
                    toastr.success("回收申请受理成功");
                } else {
                    toastr.warning("回收申请受理失败，请稍后再尝试");
                }
                setTimeout(function () {
                    window.location.href = "/custom/?pageIndex=3"
                }, 1000)
            }
        });
    });

});