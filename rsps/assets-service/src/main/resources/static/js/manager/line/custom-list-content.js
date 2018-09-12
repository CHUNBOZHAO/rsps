$(function () {

    $('#custom_list_table').DataTable({
        ordering: false,
        aLengthMenu:[50,100,200],
        paging: true,
        processing:true,
        deferRender: true,
        serverSide:true,
        ajax:{
            url:"/manager/line/custom/list/data",
            dataSrc:function (json) {
                $("#unrelated_count").text(json.otherCount);
                return json.data;
            }
        },
        columns: [
            { "data": "customerId" },
            { "data": "name" },
            { "data": "relatedLines", "defaultContent":""},
        ],
        columnDefs: [
            {
                "render": function ( data, type, row ) {
                    return '<span class="btn-related-line" style="color: #2caef5;cursor: pointer" customId="' + row.customerId + '" customName="' + row.name + '">关联线路</span>'
                },
                "targets": 3
            }
        ],
        createdRow : function( row, data, dataIndex ) {
            if (data.relatedLines == "") {
                $(row).children().eq(2).text("无");
                $(row).css("color", '#9e9e9e');
            }
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