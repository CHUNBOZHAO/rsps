$(function () {

    // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 加载数据
    $.ajax({
        url:"/manager/line/list",
        dataType:"html",
        success:function (content) {
            $("#line_content").html(content);
        }
    });

    // 新增
    $(document).on("click", ".line-add", function () {
        window.location.href = "/manager/line/show";
    });

    // 关联客户事件
    $(document).on("click", ".btn-related-custom", function () {
        var lineId = $(this).attr("lineId");
        var lineName = $(this).attr("lineName");
        window.location.href = "/manager/line/related/custom/show?lineId=" + lineId + "&lineName=" + lineName;
    });

    // 关联中转事件
    $(document).on("click", ".btn-related-transit", function () {
        var lineId = $(this).attr("lineId");
        var lineName = $(this).attr("lineName");
        var curTransferId = $(this).attr("curTransferId");
        window.location.href = "/manager/line/related/transfer/show?lineId=" + lineId + "&lineName=" + lineName + "&curTransferId=" + curTransferId;
    });

    // 关联中转事件
    $(document).on("click", ".btn-related-operator", function () {
        var lineId = $(this).attr("lineId");
        var lineName = $(this).attr("lineName");
        window.location.href = "/manager/line/related/operator/show?lineId=" + lineId + "&lineName=" + lineName;
    });

    // 编辑
    $(document).on("click", ".btn-line-edit", function () {
        var lineId = $(this).attr("lineId");
        window.location.href = "/manager/line/show?lineId=" + lineId;
    });

    // 删除
    $(document).on("click", ".btn-line-del", function () {
        var lineId = $(this).attr("lineId");
        $.confirm({
            title: '提示',
            content: '确定删除该线路吗？',
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
                            url:"/manager/line/del/handle/" + lineId,
                            success:function (msg) {
                                if (msg == "success") {
                                    toastr.success("删除成功");
                                    setTimeout(function () {
                                        window.history.go(0);
                                    }, 2000)

                                } else {
                                    toastr.error("删除失败");
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