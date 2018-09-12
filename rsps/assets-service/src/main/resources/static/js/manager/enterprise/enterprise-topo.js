$(function () {

    // 用户显示
    $(".sys-user-mode").click(function () {
        window.location.href = "/manager/enterprise/list/show";
    });

    var userTopoChart = echarts.init(document.getElementById('list_content'));
    var topoOption = {
        title : {
            text: ''
        },
        toolbox: {
            show : true,
        },
        series : [
            {
                name:'树图',
                type:'tree',
                orient: 'horizontal',  // vertical horizontal
                rootLocation: {x: 100,y: 230}, // 根节点位置  {x: 100, y: 'center'}
                nodePadding: 8,
                layerPadding: 200,
                hoverable: false,
                roam: true,
                symbolSize: 6,
                itemStyle: {
                    normal: {
                        color: '#4883b4',
                        label: {
                            show: true,
                            position: 'right',
                            formatter: "{b}",
                            textStyle: {
                                color: '#000',
                                fontSize: 12
                            }
                        },
                        lineStyle: {
                            color: '#ccc',
                            type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                        }
                    },
                    emphasis: {
                        color: '#4883b4',
                        label: {
                            show: false
                        },
                        borderWidth: 0
                    }
                },

                data: [

                ]
            }
        ]
    };

    // 请求包装状态饼图数据
    $.ajax({
        url:"/manager/sysUser/topology/data",
        dataType:"json",
        success:function (msg) {
            topoOption.series[0].data = msg;
            userTopoChart.setOption(topoOption);
        },
        error:function () {
            userTopoChart.setOption(topoOption);
        }
    });


});