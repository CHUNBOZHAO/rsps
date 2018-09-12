$(function () {

    $('#box_operator_list_table').DataTable({
        ordering: false,
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });

    // 包装箱查看点击事件
    $(document).on("click", ".box_detail_show", function () {
        var operatorId = $(this).attr("operatorId");
        var boxStatus = $(this).attr("boxStatus");
        var operator = $(this).attr("operator");
        window.location.href = "/manager/box/queryOperatorBoxes?operatorId=" + operatorId + "&boxStatus=" + boxStatus + "&operator=" + operator;
    });

});
