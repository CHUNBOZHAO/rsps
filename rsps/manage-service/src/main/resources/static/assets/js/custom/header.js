$(function () {

    // 安全退出
    $("#index_header_exit").click(function () {
        window.location.href = "/custom/handleLogout";
    });

    // 密码修改
    $("#index_header_modify_pwd").click(function () {
        window.location.href = "/custom/my/showModifyPwd";
    });

});
