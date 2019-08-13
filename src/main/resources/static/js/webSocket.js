$(function () {
    var path = window.location.href.substring(0,21);
    var pid = $("#userpid").val()
    //连接webSocket
    var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/chat-room/"+pid);
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + path + "/ws"+pid);
    } else {
        websocket = new SockJS("http://" + path + "/ws/sockjs"+pid);
    }
    websocket.onopen = function(event) {
        console.log("WebSocket:已连接");
        // console.log(event);
    };
    websocket.onmessage = function(event) {
        var data=JSON.parse(event.data);
        console.log("WebSocket:收到一条消息",data);

        var textCss=data.sendId== pid ?"bubble me":"bubble you";

        var str = "<div class='"+textCss+"'>"+ new Date(data.sendTime).Format("yyyy-MM-dd hh:mm:ss") + "<br/>" + data.message +"</div>"
        $("#message").append(str);
        scrollToBottom();
    };
    websocket.onerror = function(event) {
        console.log("WebSocket:发生错误 ");
        // console.log(event);
    };
    websocket.onclose = function(event) {
        console.log("WebSocket:已关闭");
        // console.log(event);
    }
    function sendMsg (){
        var v=$("#content").val();
        if(v==""){
            alert("消息内容不能为空！");
        }else{
            var data={};
            data["sendId"]=pid;
            data["receiveId"]=receiveId;
            data["message"]=v;
            websocket.send(JSON.stringify(data));
            scrollToBottom();
            $("#content").val("");
        }
    }

    function scrollToBottom(){
        var div = document.getElementById('message');
        div.scrollTop = div.scrollHeight;
    }

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    function clearAll(){
        $("#content").empty();
    }

    $("#sendMsg").on("click" , function(){
        alert("发送")
        sendMsg ();
        console.info(receiveId)
    })
})