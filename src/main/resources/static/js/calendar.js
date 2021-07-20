window.onload = function () {

    function calendar() {
        var calendarElement = document.getElementById('calendar');
        var todayTimeElement = document.getElementById('todayTime');
        var todayDateElement = document.getElementById('todayDate');
        var selectYearElement = document.getElementById('selectYear');
        var selectMonthElement = document.getElementById('selectMonth');
        var showTableElement = document.getElementById('showTable');
        calendarElement.style.display = 'block';

        /*
         * 获取今天的时间
         * */
        var today = new Date();

        //设置日历显示的年月
        var showYear = today.getFullYear();
        var showMonth = today.getMonth();

        //持续更新当前时间
        updateTime();

        //显示当前的年月日星期
        todayDateElement.innerHTML = getDate(today);

        //动态生成选择年的select
        for (var i = 1970; i < 2099; i++) {
            var option = document.createElement('option');
            option.value = i;
            option.innerHTML = i;
            if (i == showYear) {
                option.selected = true;
            }
            selectYearElement.appendChild(option);
        }
        //动态生成选择月的select
        for (var i = 1; i < 13; i++) {
            var option = document.createElement('option');
            option.value = i - 1;
            option.innerHTML = i;
            if (i == showMonth + 1) {
                option.selected = true;
            }
            selectMonthElement.appendChild(option);
        }

        //初始化显示table
        showTable();

        //选择年
        selectYearElement.onchange = function () {
            showYear = this.value;
            showTable();
            showOption();
        }

        //选择月
        selectMonthElement.onchange = function () {
            showMonth = Number(this.value);
            showTable();
            showOption();
        }

        /*
         * 实时更新当前时间
         * */
        function updateTime() {
            var timer = null;
            //每个500毫秒获取当前的时间，并把当前的时间格式化显示到指定位置
            var today = new Date();
            todayTimeElement.innerHTML = getTime(today);
            timer = setInterval(function () {
                var today = new Date();
                todayTimeElement.innerHTML = getTime(today);
            }, 500);
        }

        function showTable() {
            showTableElement.tBodies[0].innerHTML = '';
            //根据当前需要显示的年和月来创建日历
            //创建一个要显示的年月的下一个的日期对象
            var date1 = new Date(showYear, showMonth + 1, 1, 0, 0, 0);
            //对下一个月的1号0时0分0秒的时间 - 1得到要显示的年月的最后一天的时间
            var date2 = new Date(date1.getTime() - 1);
            //得到要显示的年月的总天数
            var showMonthDays = date2.getDate();
            //获取要显示的年月的1日的星期,从0开始的星期
            date2.setDate(1);
            //showMonthWeek表示这个月的1日的星期，也可以作为表格中前面空白的单元格的个数
            var showMonthWeek = date2.getDay();

            var cells = 7;
            var rows = Math.ceil((showMonthDays + showMonthWeek) / cells);

            //通过上面计算出来的行和列生成表格
            //没生成一行就生成7列
            //行的循环
            for (var i = 0; i < rows; i++) {
                var tr = document.createElement('tr');
                //列的循环
                for (var j = 0; j < cells; j++) {
                    var td = document.createElement('td');
                    var v = i * cells + j - (showMonthWeek - 1);
                    //根据这个月的日期控制显示的数字
                    //td.innerHTML = v;
                    if (v > 0 && v <= showMonthDays) {
                        //高亮显示今天的日期
                        if (today.getFullYear() == showYear && today.getMonth() == showMonth && today.getDate() == v) {
                            td.className = 'today';
                        }

                        td.innerHTML = v;
                    } else {
                        td.innerHTML = '';
                    }

                    td.ondblclick = function () {
                        calendarElement.style.display = 'none';

                        text1.value = showYear + '年' + (showMonth + 1) + '月' + this.innerHTML + '日';
                    }

                    tr.appendChild(td);
                }
                showTableElement.tBodies[0].appendChild(tr);

            }
        }

        function showOption() {
            var date = new Date(showYear, showMonth);
            var sy = date.getFullYear();
            var sm = date.getMonth();
            var options = selectYearElement.getElementsByTagName('option');
            for (var i = 0; i < options.length; i++) {
                if (options[i].value == sy) {
                    options[i].selected = true;
                }
            }
            var options = selectMonthElement.getElementsByTagName('option');
            for (var i = 0; i < options.length; i++) {
                if (options[i].value == sm) {
                    options[i].selected = true;
                }
            }
        }
    }

    /*
     * 获取指定时间的时分秒
     * */
    function getTime(d) {
        return [
            addZero(d.getHours()),
            addZero(d.getMinutes()),
            addZero(d.getSeconds())
        ].join(':');
    }

    /*
     * 获取指定时间的年月日和星期
     * */
    function getDate(d) {
        return d.getFullYear() + '年' + addZero(d.getMonth() + 1) + '月' + addZero(d.getDate()) + '日 星期' + getWeek(d.getDay());
    }

    /*
     * 给数字加前导0
     * */
    function addZero(v) {
        if (v < 10) {
            return '0' + v;
        } else {
            return '' + v;
        }
    }

    /*
     * 把数字星期转换成汉字星期
     * */
    function getWeek(n) {
        return '日一二三四五六'.split('')[n];
    }
    //调用显示函数
    calendar();
}