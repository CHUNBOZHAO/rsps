$(function () {

    // 是否选中
    $.isChecked = function () {
        var hasChecked = false;
        $(".ck-item").each(function () {
            if ($(this).prop("checked")) {
                hasChecked = true;
                return false;
            }
        });
        return hasChecked;
    }

    // 全选、取消全选
    $(document).on("click", "#ck_all", function () {
        if ($(this).prop("checked")) {
            $(".ck-item").prop("checked", true);
            console.log("hello checked~")
        } else {
            $(".ck-item").prop("checked", false);
            console.log("hello unchecked")
        }
    });

    // 单选
    $(document).on("click", ".ck-item", function () {
        if ($(this).prop("checked")) {
            var allChecked = true;
            $(".ck-item").each(function () {
                if (!$(this).prop("checked")) {
                    allChecked = false;
                    return false;
                }
            });
            if (allChecked) {
                $("#ck_all").prop("checked", true);
            }
        } else {
            var allChecked = true;
            $(".ck-item").each(function () {
                if (!$(this).prop("checked")) {
                    allChecked = false;
                    return false;
                }
            });
            if (!allChecked) {
                $("#ck_all").prop("checked", false);
            }
        }

    });



});