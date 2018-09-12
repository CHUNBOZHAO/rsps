$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 加载数据
    $.ajax({
        url:"/manager/line/custom/list/content",
        dataType:"html",
        success:function (content) {
            $("#related_content").html(content);
        }
    });

    // 关联线路
    $(document).on("click", ".btn-related-line", function () {
        var customId = $(this).attr("customId");
        var customName = $(this).attr("customName");
        window.location.href = "/manager/line/related/line/show?customId=" + customId + "&customName=" + customName;
    });


});