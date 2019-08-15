    var pid = $("#userpid").val()
    var path = window.location.href.substring(0,21);
console.info(pid)
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
        if(data.type == 0){  //普通消息
            var textCss=data.sendId== pid ?"bubble me":"bubble you";
            var str = "<div class='"+textCss+"'>"+ new Date(data.sendTime).Format("yyyy-MM-dd hh:mm:ss") + "<br/>" + data.message +"</div>"
            $("#message").append(str);
            scrollToBottom();
        }else if(data.type == 1){  //好友验证消息
            var ok = "对方通过了您的请求！"
            var no = "对方拒绝了您的请求！"
            var relation = {};
            var message={};
            relation.myId = data.sendId;
            relation.friendId = data.receiveId;
            message["sendId"]=data.sendId;
            message["receiveId"]=data.receiveId;
            message["type"]=3;

            if (confirm(data.message)) {
                relation.status = 1;
                message["message"]=ok;
                //发送请求修改好友关系状态，并发送通知消息
                $.ajax({
                    url : "http://localhost:8080/passfriendReq",
                    type :"post",
                    async : false,
                    data : {
                        "str" : JSON.stringify(relation)
                    },
                    success : function (resp) {
                        if(resp){
                            websocket.send(JSON.stringify(message));
                        }
                    }
                })
            } else {
                message["message"]=no;
                websocket.send(JSON.stringify(message));
            }

        }else if(data.type == 3){  //通知消息
            alert(data.message)
        }


        };
    websocket.onerror = function(event) {
        console.log("WebSocket:发生错误 ");
        // console.log(event);
    };
    websocket.onclose = function(event) {
        console.log("WebSocket:已关闭");
        // console.log(event);
    }


    function sendMsg (check){
        if(check == null){  //普通消息
            var v=$("#content").val();
            if(v==""){
                alert("消息内容不能为空！");
            }else{
                var data={};
                data["sendId"]=pid;
                data["receiveId"]=receiveId;
                data["message"]=v;
                data["type"]=0;
                websocket.send(JSON.stringify(data));
                scrollToBottom();
                $("#content").val("");
            }
        }else{ //好友验证消息
            var data={};
            data["sendId"]=pid;
            data["receiveId"]=check;
            data["type"]=1;
            websocket.send(JSON.stringify(data));
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
        sendMsg (null);
    })



