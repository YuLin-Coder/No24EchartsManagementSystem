/**
 * 用于echarts图表数据的操作
 * 作者：王晓丰
 * 日期：2020-10-19
 * ----------------------------------
 * 1、创建   王晓丰   2020-10-19
 */
$(function () {
    $(document).mousemove(function (e) {
        if (!!this.move) {
            var posix = !document.move_target ? {
                    'x': 0,
                    'y': 0
                } : document.move_target.posix,
                callback = document.call_down || function () {
                    $(this.move_target).css({
                        'top': e.pageY - posix.y,
                        'left': e.pageX - posix.x
                    });
                };

            callback.call(this, e, posix);
            console.log(posix)
        }
    }).mouseup(function (e) {
        if (!!this.move) {
            var callback = document.call_up || function () {
            };
            callback.call(this, e);
            $.extend(this, {
                'move': false,
                'move_target': null,
                'call_down': false,
                'call_up': false
            });
        }
    });

    var domlength = document.getElementsByClassName('chart').length;
    var vars = {}; //批量定义
    var vars2 = {};
    var _this = '';
    var drag = {};

    // localStorage.clear();
    for (var i = 1; i < domlength + 1; i++) {
        var varName = 'box' + i; //动态定义大DIV变量名
        var varName2 = 'box2' + i; //动态定义小DIV变量名
        var nodename = 'node' + i;
        var dragname = 'drag' + i;
        var divname = 'div' + i;
        vars[varName] = $('#' + divname).mousedown(function (e) { //动态赋值

            var offset = $(this).offset();
            this.posix = {
                'x': e.pageX - offset.left,
                'y': e.pageY - offset.top
            };

            $.extend(document, {
                'move': true,
                'move_target': this
            });


        })



        vars2[varName2] = $('#coor' + i).mousedown(function (e) {
            console.log($(this).parent()[0].id)
            _this = '#' + $(this).parent()[0].id
            var posix = {
                'w': $(_this).width(),
                'h': $(_this).height(),
                'x': e.pageX,
                'y': e.pageY
            };

            $.extend(document, {
                'move': true,
                'call_down': function (e) {
                    $(_this).css({
                        'width': Math.max(30, e.pageX - posix.x + posix.w),
                        'height': Math.max(30, e.pageY - posix.y + posix.h)
                    });
                }
            });
            // return false;
        })


    }
    console.log(vars);
    console.log(vars2);
    console.log(drag);


});
