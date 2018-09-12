$(function () {
    $('.input-daterange').datepicker({
            format: 'yyyy-mm-dd',
            language: "zh-CN",
            daysOfWeekHighlighted: "0,6"
     });

    // 包装箱操作柱状图
    var exportFlag = true;
    var boxStatusColumnChart = echarts.init(document.getElementById('box_statistics_column'));
    var boxStatusColumnOption = {
        title : {
            text: '',
            subtext: '',
            x:'center'
        },
         tooltip : {
            trigger: 'axis',
            formatter: '{c}%'
        },
        // legend: {
        //     data:['出货量']
        // },
        color:['#00CCFF'],
        grid: {
            left: '20',
            right: 'auto',
            bottom: 'auto',
            containLabel: true
        },
        // toolbox: {
        //     show : true,
        //     right:"5%",
        //     feature : {
        //         myTool1: {
        //             show: true,
        //             title: '导出报表',
        //             icon: 'image:///assets/echarts/download.png',
        //             onclick: function () {
        //                 if (exportFlag) {
        //                     exportFlag = false;
        //                     window.location.href = "/manager/index/shipments/export";
        //
        //                     setTimeout(function () {
        //                         exportFlag = true;
        //                     }, 8000);
        //                 }
        //             }
        //         }
        //     }
        // },
        calculable : true,
        xAxis : [
            {
                type : 'category',
//                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                data : []
            }
        ],
        yAxis : [
            {
                name: '闲置率趋势',
                type: 'value',
                axisLabel:{
                    formatter: '{value}%'
                },
            }
        ],
        series : [
             {
                 name:'闲置率',
                 type:'bar',
                 barWidth : 45,//柱图宽度
                 data:[],
                 markLine : {
                     data : [
                         {type : 'average', name: '平均值'}
                     ]
                 },
                 itemStyle:{
                     normal:{
                         label:{
                             formatter: '{c}%'
                         }
                     }
                  }
             }
        ]
    };


    $("#clickA").click(function(){
       var a = $("#clickA").attr("effi");
     // 请求包装箱状态柱状图
     $.ajax({
        url:"/manager/operation/getMonthChartData?effic="+a,
        dataType:"json",
        success:function (msg) {
            console.log(msg);
            boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
            boxStatusColumnOption.series = msg.seriesData;
            boxStatusColumnChart.setOption(boxStatusColumnOption);
        },
        error:function () {
            boxStatusColumnChart.setOption(boxStatusColumnOption);
        }
    });
       boxStatusColumnChart.setOption(boxStatusColumnOption);
    })

    $("#clickB").click(function(){
     var a = $("#clickB").attr("effi");
    // 请求包装箱状态柱状图
        $.ajax({
            url:"/manager/operation/getMonthChartData?effic="+a,
            dataType:"json",
            success:function (msg) {
                console.log(msg);
                boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
                boxStatusColumnOption.series = msg.seriesData;
                boxStatusColumnChart.setOption(boxStatusColumnOption);
            },
            error:function () {
                boxStatusColumnChart.setOption(boxStatusColumnOption);
            }
        });
       boxStatusColumnChart.setOption(boxStatusColumnOption);
     })
     $("#clickC").click(function(){
     var a = $("#clickC").attr("effi");
         // 请求包装箱状态柱状图
             $.ajax({
                 url:"/manager/operation/getMonthChartData?effic="+a,
                 dataType:"json",
                 success:function (msg) {
                     console.log(msg);
                     boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
                     boxStatusColumnOption.series = msg.seriesData;
                     boxStatusColumnChart.setOption(boxStatusColumnOption);
                 },
                 error:function () {
                     boxStatusColumnChart.setOption(boxStatusColumnOption);
                 }
             });
        boxStatusColumnChart.setOption(boxStatusColumnOption);
     })
      $("#clickD").click(function(){
      var a = $("#clickD").attr("effi");
          // 请求包装箱状态柱状图
              $.ajax({
                  url:"/manager/operation/getMonthChartData?effic="+a,
                  dataType:"json",
                  success:function (msg) {
                      console.log(msg);
                      boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
                      boxStatusColumnOption.series = msg.seriesData;
                      boxStatusColumnChart.setOption(boxStatusColumnOption);
                  },
                  error:function () {
                      boxStatusColumnChart.setOption(boxStatusColumnOption);
                  }
              });
        boxStatusColumnChart.setOption(boxStatusColumnOption);
     })

    // 请求包装箱状态柱状图
    $.ajax({
        url:"/manager/operation/getMonthChartData",
        dataType:"json",
        success:function (msg) {
            console.log(msg);
            boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
            boxStatusColumnOption.series = msg.seriesData;
            boxStatusColumnChart.setOption(boxStatusColumnOption);
        },
        error:function () {
            boxStatusColumnChart.setOption(boxStatusColumnOption);
        }
    });
    boxStatusColumnChart.setOption(boxStatusColumnOption);

  /*  boxStatusColumnChart.on('click', function (params) {  // 点击柱状图事件
        if (params.componentType == 'series' && params.componentSubType == 'bar' && params.seriesType == 'bar') {

            console.log(params.dataIndex);
            window.location.href = "/manager/index/shipments/show?dataIndex=" + params.dataIndex;
        }
    });*/

    $(".changEnt").click(function(){
       var entId = $(this).attr("entId")
       alert(entId);

       // 请求包装箱状态柱状图
             $.ajax({
                 url:"/manager/operation/getMonthChartData?entId="+entId,
                 dataType:"json",
                 success:function (msg) {
                     console.log(msg);
                     boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
                     boxStatusColumnOption.series = msg.seriesData;
                     boxStatusColumnChart.setOption(boxStatusColumnOption);
                 },
                 error:function () {
                     boxStatusColumnChart.setOption(boxStatusColumnOption);
                 }
             });

           boxStatusColumnChart.setOption(boxStatusColumnOption);

        })

    $("#all").click(function(){
        alert("all")
        $.ajax({
               url:"/manager/operation/getMonthChartData",
               dataType:"json",
               success:function (msg) {
                   console.log(msg);
                   boxStatusColumnOption.xAxis[0].data = msg.xAxisData;
                   boxStatusColumnOption.series = msg.seriesData;
                   boxStatusColumnChart.setOption(boxStatusColumnOption);
               },
               error:function () {
                   boxStatusColumnChart.setOption(boxStatusColumnOption);
               }
           });
           boxStatusColumnChart.setOption(boxStatusColumnOption);
  });

});