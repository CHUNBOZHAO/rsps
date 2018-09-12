$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 加载数据
    $.ajax({
        url:"/manager/user/list/data",
        dataType:"html",
        success:function (content) {
            $("#list_content").html(content);
        }
    });

    // 表格中文配置配置
    $('#user_list_table').DataTable({
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

    // 新增
    $(document).on("click", "#btn_user_add", function () {
       window.location.href = "/manager/user/show";
    });

    // 编辑
    $(document).on("click", ".user-edit", function () {
        var id = $(this).attr("id");
        window.location.href = "/manager/user/show?id=" + id;
    });

    // 重置密码点击事件
    $(document).on("click",".user-reset-pwd",function () {
        var id = $(this).attr("id");
        $.confirm({
            title: '提示',
            content: '确定重置密码吗？',
            icon: 'fa fa-info',
            // type: 'orange',
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
                            url:"/manager/user/resetPwd",
                            data:"id=" + id,
                            dataType:"json",
                            success:function (msg) {
                                if (msg.result == "success") {
                                    toastr.success("重置密码成功");
                                } else {
                                    toastr.error("重置密码失败，请联系管理员");
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

    // 删除用户点击事件
    $(document).on("click",".user-delete",function () {
        var id = $(this).attr("id");
        $.confirm({
            title: '警告',
            icon: 'fa fa-warning',
            content: '确定删除该用户吗？',
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
                            url:"/manager/user/delete",
                            data:"id=" + id,
                            dataType:"json",
                            success:function (msg) {
                                if (msg.result == "success") {
                                    toastr.success("删除用户成功");
                                    window.history.go(0);
                                } else {
                                    toastr.error("删除用户失败，请联系管理员");
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
