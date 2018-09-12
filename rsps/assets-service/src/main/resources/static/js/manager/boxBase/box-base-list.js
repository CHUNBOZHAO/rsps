$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 加载数据
    $.ajax({
        url:"/manager/boxBase/list/content",
        dataType:"html",
        success:function (content) {
            $("#list_content").html(content);
        }
    });

    // 详情
    $(document).on("click", ".btn-detail", function () {
        var id = $(this).attr("id");
        window.location.href = "/manager/boxBase/list/box/detail?id=" + id;
    });

    // 位置信息
    $(document).on("click", ".btn-map", function () {
        var id = $(this).attr("id");
        window.location.href = "/manager/location/box/showAdmin?id=" + id;
    });

});
