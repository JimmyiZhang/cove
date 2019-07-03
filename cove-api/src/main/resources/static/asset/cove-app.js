(function() {
    var uppy;

    // 输出对象
    var exports = {};

    /* 发送数据
    * 参数说明
    * option = {
    *    url:'',
    *    method: 'GET',
    *    data:{},
    *    onSuccess:function(json){}
    * }
    */
    exports.send = function(option) {
        var body = '';
        switch(option.method){
            case 'GET':
                if(option.url.indexOf('?')==-1){
                    option.url=option.url+'?';
                }
                Object.keys(option.data).forEach(function(key) {
                    option.url = option.url + key + "=" + option.data[key] + "&";
                });
            break;
            case 'POST':
                body = JSON.stringify(option.data);
            break;
        }

        var authorization = '';

        fetch(option.url, {
                method: option.method,
                mode: 'cors',
                headers: {
                  'Authorization': authorization,
                  'Content-Type': 'application/json'
                }
                body: body
            })
            .then(function(response) {
                response.json().then(function(json) {
                if(option.onSuccess) {
                    option.onSuccess(json);}
                });
            })
            .catch(function(error){
                if(option.onError){
                    option.onError(error);
                }
            });
    };

    /* 延迟
    * 参数说明
    * option = {
    *    onAction: function(){},
    *    delay: 10
    * }
    */
    exports.delay = function(option){
        setTimeout(option.onAction, delay*1000);
    }

    // 去登录
    exports.toLogin = function(){
        window.location.href = 'login.html';
    }

    // 去首页
    exports.toHome = function(){
        window.location.href='index.html';
    }

    if(!window.cove) window.cove={};
    window.cove.app = exports;
})();


