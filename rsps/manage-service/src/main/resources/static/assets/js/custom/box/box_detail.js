$(function () {

    // 地图模式点击按钮
    $("#box_detail_map_btn").click(function () {
        $("#box_detail_map").show();
        $("#box_detail_word").hide();

        $(this).css("border-bottom","2px solid #0288D1");
        $("#box_detail_word_btn").css("border-bottom","0px");
    });

    // 文字模式点击按钮
    $("#box_detail_word_btn").click(function () {
        $("#box_detail_map").hide();
        $("#box_detail_word").show();

        $(this).css("border-bottom","2px solid #0288D1");
        $("#box_detail_map_btn").css("border-bottom","0px");
    });

});