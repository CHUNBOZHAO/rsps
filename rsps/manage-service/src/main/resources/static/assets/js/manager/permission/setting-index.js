$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 点击确定事件
    $(document).on("click", "#permission_form_ok", function () {
        $("#permission_form").ajaxSubmit({
            dataType:"text",
            success:function (msg) {
                if (msg == "success") {
                    toastr.success("配置成功");
                } else {
                    toastr.error("配置权限失败");
                }
            }
        });
    });



});
