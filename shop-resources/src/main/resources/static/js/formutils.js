
function submit(url,form){

    // 1.把表单对象封装成一个js对象
    var param = formToObject(form);

    // 2.发送请求
    sendRequest(url,param);
}


function sendRequestFlush(url,param) {
    // 4.异步发送请求
    $.post(url,param,function(data){

        // 5.响应用户
        var icon = 2;
        if(data.status =="success"){
            icon = 1;
        }
        layer.msg(data.msg, {icon: icon,time: 1000},function(){
            location.reload();
        });
    },"json");
}


function sendRequest(url,param) {
    // 4.异步发送请求
    $.post(url,param,function(data){

        // 5.响应用户
        responseClinet(data);

    },"json");
}

function responseClinet(data) {
    var icon = 2;
    if(data.status =="success"){
        icon = 1;
    }
    layer.msg(data.msg, {icon: icon,time: 1000},function(){
        if(data.status =="success"){
            layer_close();
        }
    });
}

function layer_close(){
    // 获取当前iframe弹框的索引
    var index = parent.layer.getFrameIndex(window.name);
    // 2.关闭
    parent.layer.close(index);
}


// 把表单对象封装成一个js对象
function formToObject(form) {

    // 1.表单序列化
    var formArray = form.serializeArray();

    // 2.创建参数对象
    var param = new Object();
//                param.name="admin";

    // 3.把数组中的数组中的数据封装到param对象中
    for(var i = 0;i<formArray.length;i++){
        var name = formArray[i].name;
        var value = formArray[i].value;
        param[name]=value;
    }

    return param;
}