$(function(){

    // 选择包装箱颜色
    // $(".radio-box-color").click(function () {
    //     var colorValue = $(this).find("input[type='radio']").val();
    //     $(this).parent().find("input[name='color']").val(colorValue);
    // });

    // 减数量
    $(".quantity-remove").click(function (event) {
        var countObj = $(this).next();
        var curCount = countObj.val();
        curCount = parseInt(curCount) - 1;
        if (curCount < 1) {
            curCount = 1;
        }
        countObj.val(curCount);
        event.stopPropagation();
    });

    // 加数量
    $(".quantity-add").click(function (event) {
        var countObj = $(this).prev();
        var curCount = countObj.val();
        curCount = parseInt(curCount) + 1;
        if (curCount > 8) {
            curCount = 8;
        }
        countObj.val(curCount);
        event.stopPropagation();
    });

    // 选择包装箱
    $(".box-type-div").click(function () {
        var selectObj = $(this).find("div[class='box-select-flag']");
        if ($(this).hasClass("box-type-style")) {
            $(this).removeClass("box-type-style");
            selectObj.hide();
        } else {
            $(this).addClass("box-type-style");
            selectObj.show();
        }
    });

    // 选择包装箱"确定事件"
    $(".btn-select-box").click(function () {
        var param = "";
        var length = $(".box-type-style").size();
        $(".box-type-style").each(function (i, n) {
            var id = $(this).find("input[name='id']").val();
            var name = $(this).find("input[name='name']").val();
            var size = $(this).find("input[name='size']").val();
            var color = $(this).find("input[name='color']").val();
            var count = $(this).find("input[name='count']").val();

            var subParam = '{"typeId":"' + id + '", "name":"' + name + '", "size":"' + size + '", "color":"' + color + '","count":"' + count + '"}';
            if (parseInt(i) < (parseInt(length) - 1)) {
                param += subParam + ",";
            } else {
                param += subParam;
            }
        });
        param = "[" + param + "]";

        $.ajax({
            url:"/custom/order/selectBoxType",
            type:"post",
            contentType : 'application/json;charset=utf-8',
            data:param,
            success:function (msg) {
                window.location.href = "/custom/order/showOrder";
            }
        });

    });

});