$(function(){
    var table = $('#box_base_list_table').DataTable({
        ordering: false,
        aLengthMenu:[20,100,500],
        paging: true,
        processing:true,
        deferRender: true,
        serverSide:true,
        bStateSave:true,
        ajax:{
            url:"/manager/boxBase/list/data",
            dataSrc:function (json) {
                return json.data;
            }
        },
        columns: [
            { "data": null ,"defaultContent":""},
            { "data": "id" ,"defaultContent":""},
            { "data": "rfid" ,"defaultContent":""},
            { "data": "cycleIndex","defaultContent":"" },
            { "data": "recentUseTime","defaultContent":"" },
            { "data": "communicateCount","defaultContent":"" },
            { "data": "onlineTime","defaultContent":"" }
        ],
          columnDefs: [
            {
             "sTitle": '#',
             "data": null,
             "render" : function(data,type,full,meta){
                  return meta.row + 1 + meta.settings._iDisplayStart;
                 },
               "targets": 0
            },
            {
                "render": function ( data, type, row ) {
//                    if (row.lineRelated) {
//                        return '<input th:id="ck_item_' + data + '" type="checkbox" class="ck-item item-related" value="' + data + '" />';
//                    } else {
//                        return '<input th:id="ck_item_' + data + '" type="checkbox" class="ck-item item-unrelated" value="' + data + '" />';
//                    }
//                      return '<span class="btn-edit" style="color: #2caef5;cursor: pointer" th:attr="id='+row.id+'">编辑</span>nbsp;nbsp;<span class="btn-del" style="color: #2caef5;cursor: pointer" th:attr="id='+row.id+'">删除</span>'
//                    if(row.id!=null){
//                        return '<span class="btn-edit" style="color: #2caef5;cursor: pointer" id="'+ row.id +' ">编辑</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
//                        '<span class="btn-del" style="color: #2caef5;cursor: pointer" id="' + row.id +' ">删除</span>'
//                    }
                    if(row.id!=null){
                        return '<span class="btn-detail" style="color: #2caef5;cursor: pointer" id="'+ row.id +' ">详情</span>&nbsp;&nbsp;&nbsp;&nbsp;'+
                        '<span class="btn-map" style="color: #2caef5;cursor: pointer" id="' + row.id +' ">位置信息</span>'
                    }

                },
                "targets": 7
            }
        ],
//        createdRow : function( row, data, dataIndex ) {
//            $(row).children().eq(3).attr("id", "related_flag_" + data.id);
//        },

        /* fnDrawCallback : function(){
            　　this.api().column(0).nodes().each(function(cell, i) {
            　　　　cell.innerHTML =  i + 1;
            　　});
            },*/
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
//        initComplete: function () {
//        var api = this.api();
//        api.columns().indexes().flatten().each( function ( i ) {
//            var column = api.column( i-1 );
//            var select = $('<select><option value=""></option></select>')
//                .appendTo( $(column.footer()).empty() )
//                .on( 'change', function () {
//                    var val = $.fn.dataTable.util.escapeRegex(
//                        $(this).val()
//                    );
//                    column
//                        .search( val ? '^'+val+'$' : '', true, false )
//                        .draw();
//                } );
//            column.data().unique().sort().each( function ( d, j ) {
//                select.append( '<option value="'+d+'">'+d+'</option>' )
//            } );
//        } );
//    }
    });

    table.column(1).visible(false)

    $("#myInput").change(function(){
        var opt=$("#myInput").val();
        table.search(opt).draw(true);
//        table.page( 'next' ).draw( 'page' );
//        table.ajax.reload();


    });

//    $('#box_base_list_table').DataTable( {
//            initComplete: function () {
//                var api = this.api();
//                api.columns().indexes().flatten().each( function ( i ) {
//                    var column = api.column( i );
//                    var select = $('<select><option value=""></option></select>')
//                        .appendTo( $(column.footer()).empty() )
//                        .on( 'change', function () {
//                            var val = $.fn.dataTable.util.escapeRegex(
//                                $(this).val()
//                            );
//                            column
//                                .search( val ? '^'+val+'$' : '', true, false )
//                                .draw();
//                        } );
//                    column.data().unique().sort().each( function ( d, j ) {
//                        select.append( '<option value="'+d+'">'+d+'</option>' )
//                    } );
//                } );
//            }
//        } );

})
