$(function(){

  // 提示框配置
    toastr.options = {
        "positionClass": "toast-top-center", //弹出窗的位置
    }

    // 加载数据
    $.ajax({
        url:"/manager/getEnterpriseAndUserInfo",
        dataType:"html",
        success:function (content) {
            $("#list_content").html(content);
        }
    });

    //添加企业
    $(".enterpriseAndUser_add").click(function(){
         window.location.href = "/manager/enterprise/add/show";
    });


     // 编辑
    $(document).on("click", ".btn-edit", function () {
        var entId = $(this).attr("entId");
        window.location.href = "/manager/enterprise/add/show?entId=" + entId;
    });

    // 删除
    $(document).on("click", ".btn-del", function () {
        var entId = $(this).attr("entId");
        var userId = $(this).attr("userId");

        $.confirm({
            title: '提示',
            content: '确定删除该用户及其子用户吗？',
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
                            url:"/manager/delete/handle?entId=" + entId+"&userId="+userId,
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

     // 重置密码
        $(document).on("click", ".btn-reset", function () {
            var userId = $(this).attr("userId");
            $.ajax({
                url:"/manager/sysUser/password/reset/" + userId,
                success:function (msg) {
                    if (msg == "success") {
                        toastr.success("重置密码成功");
                    } else {
                        toastr.error("重置密码失败");
                    }
                }

            });
        });
     // 分配权限
    $(document).on("click", ".btn-permission", function () {
        var userId = $(this).attr("userId");
        var nickname = $(this).attr("nickname");
        var pid = $(this).attr("pid");
        window.location.href = "/manager/permission/setting/index?pid=" + pid +"&targetUserId=" + userId + "&targetNickname=" + nickname;
     });

    // 用户显示
    $(".sys-user-mode").click(function () {
        window.location.href = "/manager/enterprise/topology/index";
    });
})

