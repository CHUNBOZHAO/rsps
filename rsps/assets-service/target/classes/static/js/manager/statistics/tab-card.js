$(function(){

     var dateType = $(".tab-date-name-active").attr("value");
     var tabType = $(".tab-name-active").attr("value");
     var entId = $(".tab-rank-name-active").attr("value");

     loadRanking(tabType,dateType,entId);
     loadLineChart(tabType,dateType,entId);

    // 图表信息
     var statisticsColumnChart = echarts.init(document.getElementById('chart_area'));
     var statisticsColumnOption = {
    title : {
        text: '',
        subtext: '',
        x:''
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
             barWidth : 30,//柱图宽度
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

    // 闲置率(1)、周转率(2)、遗失率(3)、过期率(4)
    // 日(1)、周(2)、月(3)、年(4)

    // 闲置率tab点击事件
    $(document).on("click", "#t1", function () {
        $(".tab-name").removeClass("tab-name-active");
        $(this).addClass("tab-name-active");

         $("#d1").show();
         $("#d2").show();
        var entId = $(".tab-rank-name-active").attr("value");
        var dateType = $(".tab-date-name-active").attr("value");

        loadLineChart(1, dateType,entId);
    });

    // 周转率tab点击事件
    $(document).on("click", "#t2", function () {

        $(".tab-name").removeClass("tab-name-active");
        $(this).addClass("tab-name-active");

         $("#d1").hide();
         $("#d2").hide();
          $(".tab-date-name").removeClass("tab-date-name-active");
         $("#d3").addClass("tab-date-name-active");

        var entId = $(".tab-rank-name-active").attr("value");
        var dateType = $(".tab-date-name-active").attr("value");

        loadLineChart(2, dateType,entId);
    });

    // 遗失率tab点击事件
    $(document).on("click", "#t3", function () {
        $(".tab-name").removeClass("tab-name-active");
        $(this).addClass("tab-name-active");
        $("#d1").show();
        $("#d2").show();

        var entId = $(".tab-rank-name-active").attr("value");
        var dateType = $(".tab-date-name-active").attr("value");

        loadLineChart(3, dateType,entId);
    });

    // 过期率tab点击事件
    $(document).on("click", "#t4", function () {
        $(".tab-name").removeClass("tab-name-active");
        $(this).addClass("tab-name-active");

         $("#d1").show();
         $("#d2").show();
        var entId = $(".tab-rank-name-active").attr("value");
        var dateType = $(".tab-date-name-active").attr("value");

        loadLineChart(4, dateType,entId);
    });


    // 日 tab点击事件
    $(document).on("click", "#d1", function () {
        $(".tab-date-name").removeClass("tab-date-name-active");
        $(this).addClass("tab-date-name-active");

        var tabType = $(".tab-name-active").attr("value");
        var entId = $(".tab-rank-name-active").attr("value");
        loadLineChart(tabType, 1,entId);
    });

    // 周 tab点击事件
    $(document).on("click", "#d2", function () {
        $(".tab-date-name").removeClass("tab-date-name-active");
        $(this).addClass("tab-date-name-active");

        var tabType = $(".tab-name-active").attr("value");
        var entId = $(".tab-rank-name-active").attr("value");
        loadLineChart(tabType, 2,entId);
    });

    // 月 tab点击事件
    $(document).on("click", "#d3", function () {
        $(".tab-date-name").removeClass("tab-date-name-active");
        $(this).addClass("tab-date-name-active");

        var tabType = $(".tab-name-active").attr("value");
        var entId = $(".tab-rank-name-active").attr("value");
        loadLineChart(tabType, 3,entId);
    });

    // 年 tab点击事件
    $(document).on("click", "#d4", function () {
        $(".tab-date-name").removeClass("tab-date-name-active");
        $(this).addClass("tab-date-name-active");

        var tabType = $(".tab-name-active").attr("value");
        var entId = $(".tab-rank-name-active").attr("value");
        loadLineChart(tabType, 4,entId);
    });

    //排名点击
    $(document).on("click", ".tab-rank-name", function () {
        $(".tab-rank-name").removeClass("tab-rank-name-active");
        $(this).addClass("tab-rank-name-active");
         var tabType = $(".tab-name-active").attr("value");
         var dateType = $(".tab-date-name-active").attr("value");
         var entId = $(".tab-rank-name-active").attr("value");
         loadLineChart(tabType, dateType,entId)
    });

    // 展示图表区域
    function loadLineChart(tabType, dateType,entId) {
        console.log("tabType:" + tabType);
        console.log("dateType:" + dateType);
        console.log("entId:" + entId);

        // 请求包装箱状态柱状图
        $.ajax({
            url:"/manager/statistics/chart/data/get",
            data:"tabType=" + tabType + "&dateType=" + dateType+"&entId="+entId,
            dataType:"json",
            success:function (msg) {
                statisticsColumnOption.xAxis[0].data = msg.xAxisData;
                statisticsColumnOption.series = msg.seriesData;
                statisticsColumnOption.title = msg.titleData;
                statisticsColumnOption.yAxis[0].data = msg.yAxisData;

                statisticsColumnChart.setOption(statisticsColumnOption);
            },
            error:function () {
                statisticsColumnChart.setOption(statisticsColumnOption);
            }
        });
    }

    // 展示排名
    function loadRanking(tabType, dateType,entId) {
        console.log("tabType:" + tabType);
        console.log("dateType:" + dateType);
        console.log("entId:" + entId);

        $.ajax({
            url:"/manager/statistics/ranking/data/show",
            data:"tabType=" + tabType + "&dateType=" + dateType+"&entId="+entId,
            dataType:"html",
            success:function (content) {
                $("#ranking_area").html(content);
            }
        });
    }

})