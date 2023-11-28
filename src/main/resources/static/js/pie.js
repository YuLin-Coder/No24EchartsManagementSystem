function echart3() {
    let myChart3 = echarts.init(document.getElementById('echart3'));
    $.ajax({
        type : "get",
        async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/echarts/echartthl?username="+username,    //请求发送到TestServlet处
        dataType : "json",
        success:function(res){
            position=res['position']
            // 指定图表的配置项和数据
            myChart3.setOption({
                title: {
                    text: '饼状图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: res["data"].data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    });
    window.onresize = function () {
        myChart3.resize();
    }
    $("#drag3").resize(function () {
        myChart3.resize();
    })
}
