$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 表格中文配置配置
    $('#box_list_table').DataTable({
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


    // 删除用户点击事件
    $(".box-delete").click(function () {
        var rfid = $(this).attr("rfid");
        $.confirm({
            title: '警告',
            icon: 'fa fa-warning',
            content: '确定删除该包装箱吗？',
            type: 'red',
            typeAnimated: true,
            animation: 'rotateYR',
            closeAnimation: 'rotateYR',
            animateFromElement: false,
            buttons: {
                tryAgain: {
                    text: '确定',
                    // btnClass: 'btn-red',
                    action: function(){
                        $.ajax({
                            url:"/manager/box/delete",
                            data:"rfid=" + rfid,
                            dataType:"json",
                            success:function (msg) {
                                if (msg.result == "success") {
                                    toastr.success("删除包装箱成功");
                                    window.history.go(0);
                                } else {
                                    toastr.error("删除包装箱失败，请联系管理员");
                                }
                            }

                        });
                    }
                },
                close: {
                    text:'取消'
                }
            }
        });
    });


});
