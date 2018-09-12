$(function () {

    $('#shipment_record_list_table').DataTable({
        ordering: false,
        aLengthMenu:[100,200,500,1000],
        paging: false,
        // bProcessing : true,
        deferRender: true,
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
        },

    });

    // var beginDate = $("#beginDate").val();
    // var endDate = $("#endDate").val();
    // var operator = $("#operator").val();
    // var lineName = $("#lineName").val();
    // var forwarder = $("#forwarder").val();
    // var orderId = $("#orderId").val();
    // var customName = $("#customName").val();
    //
    // $('#shipment_record_list_table').DataTable({
    //     ordering: false,
    //     aLengthMenu:[500,800,1000],
    //     paging: true,
    //     processing:true,
    //     deferRender: true,
    //     serverSide:true,
    //     ajax:{
    //         url:"/manager/report/shipments/record/data/get",
    //         data:"beginDate=" + beginDate + "&endDate=" + endDate + "&operator=" + operator + "&lineName=" + lineName + "&forwarder=" + forwarder + "&orderId=" + orderId + "&customName=" + customName,
    //         dataSrc:function (json) {
    //             return json.data;
    //         }
    //     },
    //     columns: [
    //         { "data": "customerId" },
    //         { "data": "name" },
    //         { "data": "address", "defaultContent":""},
    //         { "data": "lineRelated" },
    //         { "data": "lineId" }
    //     ],
    //     language: {
    //         "sProcessing": "正在加载数据，请稍后...",
    //         "sLengthMenu": "显示 _MENU_ 项结果",
    //         "sZeroRecords": "没有匹配结果",
    //         "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    //         "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    //         "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    //         "sInfoPostFix": "",
    //         "sSearch": "搜索:",
    //         "sUrl": "",
    //         "sEmptyTable": "表中数据为空",
    //         "sLoadingRecords": "载入中...",
    //         "sInfoThousands": ",",
    //         "oPaginate": {
    //             "sFirst": "首页",
    //             "sPrevious": "上一页",
    //             "sNext": "下一页",
    //             "sLast": "末页"
    //         },
    //         "oAria": {
    //             "sSortAscending": ": 以升序排列此列",
    //             "sSortDescending": ": 以降序排列此列"
    //         }
    //     },
    //
    // });

});