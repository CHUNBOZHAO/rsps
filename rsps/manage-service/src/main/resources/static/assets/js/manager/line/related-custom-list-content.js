$(function () {

    var lineId = $("#lineId").val();
    $('#related_custom_list_table').DataTable({
        ordering: false,
        aLengthMenu:[500,800,1000],
        paging: true,
        processing:true,
        deferRender: true,
        serverSide:true,
        ajax:{
            url:"/manager/line/related/custom/list/data?lineId=" + lineId,
            dataSrc:function (json) {
                return json.data;
            }
        },
        columns: [
            { "data": "customerId" },
            { "data": "name" },
            { "data": "address", "defaultContent":""},
            { "data": "lineRelated" },
            { "data": "lineId" }
        ],
        columnDefs: [
            {
                "render": function ( data, type, row ) {
                    if (row.lineRelated) {
                        return '<input th:id="ck_item_' + data + '" type="checkbox" class="ck-item item-related" value="' + data + '" />';
                    } else {
                        return '<input th:id="ck_item_' + data + '" type="checkbox" class="ck-item item-unrelated" value="' + data + '" />';
                    }
                },
                "targets": 0
            },
            {
                "render": function ( data, type, row ) {
                    if (row.lineRelated) {
                        return '<span style="color: #2eba2b">已关联</span>';
                    } else {
                        return '<span style="color: #9e9e9e">未关联</span>';
                    }
                },
                "targets": 3
            },
            {
                "render": function ( data, type, row ) {
                    return '<span class="btn-relate" style="color: #2caef5;cursor: pointer" lineId="'+ row.lineId +'" customId="' + row.customerId + '">关联</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
                        '<span class="btn-relate-cancel" style="color: #2caef5;cursor: pointer" lineId="' + row.lineId + '" customId="' + row.customerId + '">取消关联</span>'
                },
                "targets": 4
            }
        ],
        createdRow : function( row, data, dataIndex ) {
            $(row).children().eq(3).attr("id", "related_flag_" + data.customerId);
        },
        language: {
            "sProcessing": "正在加载数据，请稍后...",
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

});