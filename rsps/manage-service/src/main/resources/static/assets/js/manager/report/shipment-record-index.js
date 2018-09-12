$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 请求查询条件页面
    $.ajax({
        url:"/manager/report/shipments/show/content",
        dataType:"html",
        success:function (content) {
            $("#index_content").html(content);
        }
    });

    $('.input-daterange').datepicker({
        format: 'yyyy-mm-dd',
        language: "zh-CN",
        daysOfWeekHighlighted: "0,6"
    });

    // 导出
    $(document).on("click", "#btn_export", function () {
        var beginDate = $("#beginDate").val();
        var endDate = $("#endDate").val();
        var operator = $("#operator").val();
        var lineName = $("#lineName").val();
        window.location.href = "/manager/report/shipments/record/export?beginDate=" + beginDate + "&endDate=" + endDate + "&operator=" + operator + "&lineName=" + lineName;
    });


});
