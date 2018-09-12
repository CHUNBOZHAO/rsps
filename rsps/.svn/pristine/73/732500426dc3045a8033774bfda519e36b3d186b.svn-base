$(function () {
    var totalBoxCount = $("#countTotal").val();

    // 包装箱状态饼图
    var boxStatusPieChart = echarts.init(document.getElementById('box_status_pie'));
    var boxStatusPieOption = {
        title : {
            text: '包装箱状态饼图',
            subtext: '包装箱总数：' + totalBoxCount + '个',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['配货','运输','滞留','回收','闲置']
        },
        color:['#ba5784','#7646cd','#f4b039','#33b781','#D3D3D3'],
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:310, name:'配货'},
                    {value:234, name:'运输'},
                    {value:135, name:'滞留'},
                    {value:1548, name:'回收'},
                    {value:335, name:'闲置'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 请求包装状态饼图数据
    $.ajax({
        url:"/manager/chart/getBoxStatusPieData",
        dataType:"json",
        success:function (msg) {
            boxStatusPieOption.series[0].data[0].value = msg[0];
            boxStatusPieOption.series[0].data[1].value = msg[1];
            boxStatusPieOption.series[0].data[2].value = msg[2];
            boxStatusPieOption.series[0].data[3].value = msg[3];
            boxStatusPieOption.series[0].data[4].value = msg[4];

            boxStatusPieChart.setOption(boxStatusPieOption);
        },
        error:function () {
            boxStatusPieChart.setOption(boxStatusPieOption);
        }
    });

    // 包装箱状态折线图
    var boxStatusLineChart = echarts.init(document.getElementById('box_status_line'));
    var boxStatusLineOption = {
        title: {
            text: '包装箱状态折线图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            // orient: 'vertical',
            left: 'right',
            data:['配货','运输','滞留','回收']
        },
        color:['#ba5784','#7646cd','#f4b039','#33b781'],
        grid: {
            left: '4%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            name: '数量(个)',
            type: 'value'
        },
        series: [
            // {
            //     name:'邮件营销',
            //     type:'line',
            //     stack: '总量',
            //     data:[120, 132, 101, 134, 90, 230, 210]
            // },
            // {
            //     name:'联盟广告',
            //     type:'line',
            //     stack: '总量',
            //     data:[220, 182, 191, 234, 290, 330, 310]
            // },
        ]
    };

    // 请求包装状态折线图数据
    $.ajax({
        url:"/manager/chart/getBoxStatusLineData",
        dataType:"json",
        success:function (msg) {
            boxStatusLineOption.xAxis.data = msg.xAxisData;
            boxStatusLineOption.series = msg.seriesData;
            boxStatusLineChart.setOption(boxStatusLineOption);
        },
        error:function () {
            boxStatusLineChart.setOption(boxStatusLineOption);
        }
    });

    // 包装箱操作柱状图
    var exportFlag = true;
    var boxStatusColumnChart = echarts.init(document.getElementById('box_status_column'));
    var boxStatusColumnOption = {
        title : {
            text: '包装箱出货量柱状图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        // legend: {
        //     data:['出货量']
        // },
        color:['#ba5784'],
        grid: {
            left: '5%',
            right: '5%',
            bottom: '3%',
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
                data : []
            }
        ],
        yAxis : [
            {
                name: '数量(个)',
                type: 'value'
            }
        ],
        series : [
            // {
            //     name:'蒸发量',
            //     type:'bar',
            //     data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            //     markLine : {
            //         data : [
            //             {type : 'average', name: '平均值'}
            //         ]
            //     }
            // }
        ]
    };

    // 请求包装箱状态柱状图
    $.ajax({
        url:"/manager/chart/box/bind/month",
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
    boxStatusColumnChart.on('click', function (params) {  // 点击柱状图事件
        if (params.componentType == 'series' && params.componentSubType == 'bar' && params.seriesType == 'bar') {

            console.log(params.dataIndex);
            window.location.href = "/manager/index/shipments/show?dataIndex=" + params.dataIndex;
        }
    });




});