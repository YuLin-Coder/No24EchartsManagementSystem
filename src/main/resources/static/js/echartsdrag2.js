/**
 * 用于echarts图表的实现以及图表的拖拉拽功能
 * 作者：王晓丰
 * 日期：2020-10-19
 * ----------------------------------
 * 1、创建   王晓丰   2020-10-19
 */

// 拖拽与发送实现
var vars = {}; //批量定义
var vars2 = {};
var drag = {};
var position = {};
$('.drag').l_zoom('free');



//name名字 value值 iDay多少天过期
function setCookie(name, value, iDay) {
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + iDay);
    document.cookie = name + "=" + value + ";expires=" + oDate;
}

//从cookie中取username
function getCookie(name) {
    var arr = document.cookie.split("; ");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        if (arr2[0] === name) {
            return arr2[1];
        }
    }
    return null;
}

function removeCookie(name) {
    setCookie(name, 1, -1);//设置一天后过期
}

var username = getCookie('userName');

// setTimeout(function () {
//     location.reload()
// }, 10000);

//刷新echarts图表的页面
window.onload = function () {
    setCookie("userName", "admin", 1);
    setCookie("password", "123456", 1);
    position.username = username;
    queryPosition(username);
    // echart1();
    // echart2();
    // echart3();
    // setTimeout(function(){
    //     updatePosition(position);
    //     location.reload()
    // },10000);
}

//从数据库中获得echarts图表的位置与大小信息
function queryPosition(username) {
    // $.ajax({
    //     type : "get",
    //     async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    //     url : "/echarts/queryPosition?username="+username,    //请求发送到TestServlet处
    //     dataType : "json",
    //     success:function(res){

    let str = '{"username":"admin","echartawidth":400.0,"echartbwidth":400.0,"echartcwidth":400.0,"echartaheight":300.0,"echartbheight":300.0,"echartcheight":300.0,"echartaxaxis":350.0,"echartbxaxis":800.0,"echartcxaxis":1250.0,"echartayaxis":170.0,"echartbyaxis":170.0,"echartcyaxis":170.0}';
   let res= JSON.parse(str);
    localStorage.clear();
    position.echartawidth = res.echartawidth;
    localStorage.setItem('drag1width', res.echartawidth);
    // position.echartbwidth = res.echartbwidth;
    // localStorage.setItem('drag2width', res.echartbwidth);
    // position.echartcwidth = res.echartcwidth;
    // localStorage.setItem('drag3width', res.echartcwidth);

    position.echartaheight = res.echartaheight;
    localStorage.setItem('drag1height', res.echartaheight);
    position.echartbheight = res.echartbheight;
    localStorage.setItem('drag2height', res.echartbheight);
    position.echartcheight = res.echartcheight;
    localStorage.setItem('drag3height', res.echartcheight);

    position.echartaxaxis = res.echartaxaxis;
    localStorage.setItem('drag1x', res.echartaxaxis);
    position.echartbxaxis = res.echartbxaxis;
    localStorage.setItem('drag2x', res.echartbxaxis);
    position.echartcxaxis = res.echartcxaxis;
    localStorage.setItem('drag3x', res.echartcxaxis);

    position.echartayaxis = res.echartayaxis;
    localStorage.setItem('drag1y', res.echartayaxis);
    position.echartbyaxis = res.echartbyaxis;
    localStorage.setItem('drag2y', res.echartbyaxis);
    position.echartcyaxis = res.echartcyaxis;
    localStorage.setItem('drag3y', res.echartcyaxis);
    //     }
    //     // });
}

// localStorage.clear();//清楚本地缓存

//折线图
function echart1() {

    let myChart1 = echarts.init(document.getElementById('echart1'));
    // $.ajax({
    //     type: "get",
    //     async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    //     url: "/echarts/echarto?username=" + username,    //请求发送到TestServlet处
    //     dataType: "json",
    //     success: function (res) {

    let str ='{"data":{"data":[820,932,901,934,1290,1330,1320],"type":"line"},"position":{"username":"admin","echartawidth":400.0,"echartbwidth":400.0,"echartcwidth":400.0,"echartaheight":300.0,"echartbheight":300.0,"echartcheight":300.0,"echartaxaxis":350.0,"echartbxaxis":800.0,"echartcxaxis":1250.0,"echartayaxis":170.0,"echartbyaxis":170.0,"echartcyaxis":170.0}}'
    let res= JSON.parse(str);
            position = res['position']
            // 指定图表的配置项和数据
            myChart1.setOption({
                title: {
                    text: '折线图'
                },
                tooltip: {
                    show: true
                },
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: res["data"]
            });
    //     }
    // });
    window.onresize = function () {
        myChart1.resize();
    }
    $("#drag2").resize(function () {
        myChart1.resize();
    })
}

//柱状图
function echart2() {
    let myChart2 = echarts.init(document.getElementById('echart2'));

    // $.ajax({
    //     type: "get",
    //     async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    //     url: "/echarts/echarttw?username=" + username,    //请求发送到TestServlet处
    //     dataType: "json",
    //     success: function (res) {
    let str ='{"data1":[0.0,-8.901463875624668,-17.025413764148556,-24.038196249566663,-29.66504684804471,-33.699527649688676,-36.00971978255796,-36.541005056170455,-35.31542466107655,-32.427752866005996,-28.038563739693934,-22.364693082297347,-15.667600860943732,-8.240217424060843,-0.3929067389459173,7.560799717904647,15.318054209871054,22.599523033552096,29.16065418543528,34.800927952557615,39.37074152590451,42.77569739999406,44.97819140223978,45.99632376477021,45.900279829731865,44.806440199911805,42.86957840395034,40.2735832137877,37.22119936652441,33.92331243435557,30.588309963978517,27.412031986865767,24.56878097935778,22.203796820272576,20.427519715115604,19.311867685884827,18.888649906111855,19.150128087782186,20.051630602288828,21.516023200879346,23.439750867099516,25.700091656548704,28.163208735293757,30.692553648214542,33.1571635093161,35.439407573791215,37.44177367693234,39.09234039030659,40.34865356244595,41.19981246258526,41.66666666666667],"position":{"username":"admin","echartawidth":400.0,"echartbwidth":400.0,"echartcwidth":400.0,"echartaheight":300.0,"echartbheight":300.0,"echartcheight":300.0,"echartaxaxis":350.0,"echartbxaxis":800.0,"echartcxaxis":1250.0,"echartayaxis":170.0,"echartbyaxis":170.0,"echartcyaxis":170.0},"xAxisData":["类目0","类目1","类目2","类目3","类目4","类目5","类目6","类目7","类目8","类目9","类目10","类目11","类目12","类目13","类目14","类目15","类目16","类目17","类目18","类目19","类目20","类目21","类目22","类目23","类目24","类目25","类目26","类目27","类目28","类目29","类目30","类目31","类目32","类目33","类目34","类目35","类目36","类目37","类目38","类目39","类目40","类目41","类目42","类目43","类目44","类目45","类目46","类目47","类目48","类目49","类目50"],"data2":[-50.0,-47.18992898088751,-42.54426104547181,-36.290773900754886,-28.71517529663627,-20.146937097399626,-10.94374119697364,-1.4752538113770308,7.893046603320797,16.81528588241657,24.979206795219028,32.11821023962515,38.02096119056733,42.53821720798438,45.58667093073836,47.14973738101559,47.275355710354944,46.07100702178889,43.6962693226927,40.35333240268025,36.275975292575026,31.71756381888028,26.938653692729076,22.194784893913152,17.725026430574392,13.741778696752679,10.422266555457615,7.902063853319403,6.270884006107842,5.570756810898967,5.796594266992678,6.899033489892203,8.7893381290192,11.346045936704996,14.42297348773613,17.858132851517098,21.483081596548438,25.132218074866262,28.651548555679597,31.906490373810854,34.788333671419466,37.21906041552118,39.154309232933485,40.58437366457342,41.5332247510366,42.05565130942339,42.23270781895,42.165745792772285,41.969375711588256,41.76375960543808,41.66666666666667]}';
    let res= JSON.parse(str);

            position = res['position']
            // 指定图表的配置项和数据
            myChart2.setOption({
                title: {
                    text: '柱状图'
                },
                legend: {
                    data: ['bar', 'bar2']
                },

                tooltip: {},
                xAxis: {
                    data: res["xAxisData"],
                    splitLine: {
                        show: false
                    }
                },
                yAxis: {},
                series: [{
                    name: 'bar',
                    type: 'bar',
                    data: res["data1"],
                    animationDelay: function (idx) {
                        return idx * 10;
                    }
                }, {
                    name: 'bar2',
                    type: 'bar',
                    data: res["data2"],
                    animationDelay: function (idx) {
                        return idx * 10 + 100;
                    }
                }],
                animationEasing: 'elasticOut',
                animationDelayUpdate: function (idx) {
                    return idx * 5;
                }
            });
    //     }
    // });
    window.onresize = function () {
        myChart2.resize();
    }
    $("#drag2").resize(function () {
        myChart2.resize();
    })
}

//饼图
function echart3() {
    let myChart3 = echarts.init(document.getElementById('echart3'));
    // $.ajax({
    //     type: "get",
    //     async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    //     url: "/echarts/echartthl?username=" + username,    //请求发送到TestServlet处
    //     dataType: "json",
    //     success: function (res) {

    let str ='{"data":{"name":"访问来源","type":"pie","data":[{"id":null,"value":555,"name":"直接访问"},{"id":null,"value":310,"name":"邮件营销"},{"id":null,"value":234,"name":"联盟广告"},{"id":null,"value":135,"name":"视频广告"},{"id":null,"value":135,"name":"搜索引擎"}]},"position":{"username":"admin","echartawidth":400.0,"echartbwidth":400.0,"echartcwidth":400.0,"echartaheight":300.0,"echartbheight":300.0,"echartcheight":300.0,"echartaxaxis":350.0,"echartbxaxis":800.0,"echartcxaxis":1250.0,"echartayaxis":170.0,"echartbyaxis":170.0,"echartcyaxis":170.0}}';
    let res= JSON.parse(str);


    position = res['position']
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
    //     }
    // });
    window.onresize = function () {
        myChart3.resize();
    }
    $("#drag3").resize(function () {
        myChart3.resize();
    })
}

//页面上的按钮保存位置和长宽信息
function saveCharts() {
    $.ajax({
        type: "post",
        data: position,
        async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/echarts/putPosition",    //请求发送到TestServlet处
        dataType: "json",
        success: function (res) {
            if (res === true) console.log("图片位置保存成功")
        }
    });
}

//页面上的还原按钮将位置和长宽还原
function revertCharts() {
    position.username = getCookie('userName');
    position.echartawidth = Number(400);
    position.echartaheight = Number(300);
    position.echartaxaxis = Number(350);
    position.echartayaxis = Number(170);

    position.echartbwidth = Number(400);
    position.echartbheight = Number(300);
    position.echartbxaxis = Number(800);
    position.echartbyaxis = Number(170);

    position.echartcwidth = Number(400);
    position.echartcheight = Number(300);
    position.echartcxaxis = Number(1250);
    position.echartcyaxis = Number(170);
    localStorage.clear();
    updatePosition(position);
    location.reload()
    $.ajax({
        type: "post",
        data: position,
        async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/echarts/putPosition",    //请求发送到TestServlet处
        dataType: "json",
        success: function (res) {
            if (res === true) console.log("图片位置还原成功")
        }
    });

}

//更新数据库中echarts图表的位置和长宽信息
function updatePosition() {
    var domlength = document.getElementsByClassName('drag').length;
    for (var i = 1; i < domlength + 1; i++) {
        var varName = 'drag' + i //动态定义大DIV变量名
        var dragname = 'drag' + i;
        let node = document.getElementById(varName)
        if (localStorage.getItem(dragname + 'width') !== null && localStorage.getItem(dragname + 'height') !== null) {
            if (dragname === 'drag1') {
                localStorage.setItem(varName + 'width', position.echartawidth)
                localStorage.setItem(varName + 'height', position.echartaheight)//读取本地存储的位置信息给div设置大小
                localStorage.setItem(node.id + 'x', position.echartaxaxis)
                localStorage.setItem(node.id + 'y', position.echartayaxis)
            }
            if (dragname === 'drag2') {
                localStorage.setItem(varName + 'width', position.echartbwidth)
                localStorage.setItem(varName + 'height', position.echartbheight)//读取本地存储的位置信息给div设置大小
                localStorage.setItem(node.id + 'x', position.echartbxaxis)
                localStorage.setItem(node.id + 'y', position.echartbyaxis)
            }
            if (dragname === 'drag3') {
                localStorage.setItem(varName + 'width', position.echartcwidth)
                localStorage.setItem(varName + 'height', position.echartcheight)//读取本地存储的位置信息给div设置大小
                localStorage.setItem(node.id + 'x', position.echartcxaxis)
                localStorage.setItem(node.id + 'y', position.echartcyaxis)
            }
        }
    }
}


function deleteE1() {
    position.username = getCookie('userName');
    position.echartaxaxis = Number(2350);

    localStorage.clear();
    updatePosition(position);
    location.reload()
    $.ajax({
        type: "post",
        data: position,
        async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/echarts/putPosition",    //请求发送到TestServlet处
        dataType: "json",
        success: function (res) {
            if (res === true) console.log("图片位置还原成功")
        }
    });

}

function deleteE2() {
    position.username = getCookie('userName');
    position.echartbxaxis = Number(2350);

    localStorage.clear();
    updatePosition(position);
    location.reload()
    $.ajax({
        type: "post",
        data: position,
        async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/echarts/putPosition",    //请求发送到TestServlet处
        dataType: "json",
        success: function (res) {
            if (res === true) console.log("图片位置还原成功")
        }
    });

}


function deleteE3() {
    position.username = getCookie('userName');
    position.echartcxaxis = Number(2350);

    localStorage.clear();
    updatePosition(position);
    location.reload()
    $.ajax({
        type: "post",
        data: position,
        async: false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/echarts/putPosition",    //请求发送到TestServlet处
        dataType: "json",
        success: function (res) {
            if (res === true) console.log("图片位置还原成功")
        }
    });

}


//echarts图表的的位置拖拉以及长宽的改变
$(document).ready(function () {
    //拖拽
    var domlength = document.getElementsByClassName('drag').length;
    for (var i = 1; i < domlength + 1; i++) {
        var _move = false; //移动标记
        var _x, _y;
        var index_x, index_y;
        var varName = 'drag' + i //动态定义大DIV变量名
        var varName2 = 'box2' + i;
        var textName = 'result' + i; //动态定义大DIV变量名
        var dragname = 'drag' + i;
        if (localStorage.getItem(dragname + 'width') !== null && localStorage.getItem(dragname + 'height') !== null) {
            width = Number(localStorage.getItem(varName + 'width'))
            height = Number(localStorage.getItem(varName + 'height'))//读取本地存储的位置信息给div设置大小
            $('#' + dragname).css({
                'width': width,
                'height': height
            });
        }
        $("#" + varName).bind("mousedown", downIndex); //div鼠标按下事件绑定
        $("#" + varName).bind("mousemove", mmove); //鼠标移动事件绑定
        $("#" + varName).bind("mouseup", mup); //鼠标弹起事件绑定
        function mmove(e) {
            if (_move) {
                var x = e.pageX - _x; //移动时根据鼠标位置计算控件左上角的绝对位置
                var y = e.pageY - _y;

                $("#" + e.currentTarget.id).css({
                    top: y,
                    left: x,
                    'z-index': "999"
                }); //div新位置
                show(x, y, e);
            }
        }

        function mup() {
            _move = false;
        }

        function downIndex(e) {
            _move = true;
            console.log($(this))
            _x = e.pageX - parseInt($("#" + e.currentTarget.id).css("left"));
            _y = e.pageY - parseInt($("#" + e.currentTarget.id).css("top"));

        }

        function show(x, y, e) {
            localStorage.setItem(e.currentTarget.id + 'x', x)
            localStorage.setItem(e.currentTarget.id + 'y', y)
            // if(e.currentTarget.id === 'drag1'){
            //     position.echartaxaxis = Number(x);
            //     position.echartayaxis =Number(y);
            // }
            // if(e.currentTarget.id === 'drag2'){
            //     position.echartbxaxis = Number(x);
            //     position.echartbyaxis =Number(y);
            // }
            // if(e.currentTarget.id === 'drag3'){
            //     position.echartcxaxis = Number(x);
            //     position.echartcyaxis =Number(y);
            // }
            //document.getElementById(e.currentTarget.id).childNodes[1].innerHTML = "x坐标为：" + x + "y坐标为：" + y +
            "(注：这是div左顶点的坐标)";
        }

        $("#" + dragname).resize(function () {
            if ($(this)[0].id === 'drag1') {
                position.echartawidth = Number($(this).width());
                position.echartaheight = Number($(this).height());
            }
            if ($(this)[0].id === 'drag2') {
                position.echartbwidth = Number($(this).width());
                position.echartbheight = Number($(this).height());
            }
            if ($(this)[0].id === 'drag3') {
                position.echartcwidth = Number($(this).width());
                position.echartcheight = Number($(this).height());
            }
            localStorage.setItem($(this)[0].id + 'width', $(this).width());
            localStorage.setItem($(this)[0].id + 'height', $(this).height());


        });

        drag[dragname] = function () {
            var y = 0,
                x = 0;
            let node = document.getElementById(varName)
            if (localStorage.getItem(varName + 'x') !== null && localStorage.getItem(varName + 'y') !== null) {
                node.style.top = Number(localStorage.getItem(varName + 'y')) - node.offsetHeight / 2 + 'px'
                node.style.left = Number(localStorage.getItem(varName + 'x')) - node.offsetWidth / 2 + 'px' //读取本地存储的位置信息给div设置位置
            }

            node.onmousedown = function (e) {
                node.onmousemove = function (e) {
                    y = e.clientY;
                    x = e.clientX;


                    node.style.top = y - node.offsetHeight / 2 + 'px'
                    node.style.left = x - node.offsetWidth / 2 + 'px'
                    document.onmouseup = function () {
                        if (node.id === 'drag1') {
                            position.echartaxaxis = Number(x);
                            position.echartayaxis = Number(y);
                        }
                        if (node.id === 'drag2') {
                            position.echartbxaxis = Number(x);
                            position.echartbyaxis = Number(y);
                        }
                        if (node.id === 'drag3') {
                            position.echartcxaxis = Number(x);
                            position.echartcyaxis = Number(y);
                        }
                        localStorage.setItem(node.id + 'x', x)
                        localStorage.setItem(node.id + 'y', y)
                        node.onmousemove = null;
                    }
                }
            }
        }
         eval('drag.' + dragname + '()');

    }

});
