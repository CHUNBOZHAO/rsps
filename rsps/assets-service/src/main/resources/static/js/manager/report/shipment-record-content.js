$(function () {

    $('.input-daterange').datepicker({
        language: "zh-CN",
        autoclose: true,
        todayHighlight: true,
        format: 'yyyy-mm-dd',
        daysOfWeekHighlighted: "0,6"
    });

    $("#forwarder").change(function(){
        var forwarder = $("#forwarder").val();
        // 请求查询条件页面
         $.ajax({
             url:"/manager/report/shipments/show/content?forwarder="+forwarder,
             dataType:"html",
             success:function (content) {
                 $("#index_content").html(content);
             }
         });
     });
});
