$(function () {

    var boxId = $(".app-container").attr("boxId");
    var orderId = $(".app-container").attr("orderId");
    var orderType = $(".app-container").attr("orderType");
    $.ajax({
        url:"/custom/order/queryTrack",
        data:"boxId=" + boxId + "&orderId=" + orderId,
        dataType:"json",
        success:function (msg) {
            if (msg.length == 0) {
                $("#box_detail_map").css("line-height","280px");
                $("#box_detail_map").css("text-align","center");
                $("#box_detail_map").html("暂无位置信息")
            } else {
                var pathDatas = new Array();
                for (var i = 0; i < msg.length; i++) {
                    var bMap = new BMap.Point(msg[i][0],msg[i][1]);
                    pathDatas.push(bMap);
                }
                var firstPoint = new BMap.Point(msg[0][0], msg[0][1]);
                var lastPoint = new BMap.Point(msg[msg.length - 1][0], msg[msg.length - 1][1])

                // 百度地图API功能
                var map = new BMap.Map("box_detail_map");
                map.centerAndZoom(lastPoint, 15);
                map.enableScrollWheelZoom();

                var polyline = new BMap.Polyline(pathDatas, {strokeColor:"blue", strokeWeight:5, strokeOpacity:0.5});   //创建折线
                map.addOverlay(polyline);   //增加折线


                // 起始位置图标
                var startIcon = new BMap.Icon("/assets/images/map/map-point-start.png", new BMap.Size(32,43));
                startIcon.setAnchor(new BMap.Size(16,43));
                var markerStart = new BMap.Marker(firstPoint,{icon:startIcon});  // 创建标注
                map.addOverlay(markerStart); // 将标注添加到地图中

                // 终点位置图标
                var endIcon = new BMap.Icon("/assets/images/map/truck32.png", new BMap.Size(32,32));  // 默认为运输汽车
                endIcon.setAnchor(new BMap.Size(16,32));
                if (orderType == 2) { //
                    endIcon = new BMap.Icon("/assets/images/map/map-point-end.png", new BMap.Size(32,43));
                    endIcon.setAnchor(new BMap.Size(16,43));
                }

                // endIcon.setAnchor(new BMap.Size(16,32));
                var markerEnd = new BMap.Marker(lastPoint,{icon:endIcon});  // 创建标注
                map.addOverlay(markerEnd); // 将标注添加到地图中
            }
        },
    })


});

