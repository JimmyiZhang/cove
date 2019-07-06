(function() {

    // 保存本地存储
    var setStorage = function(key,val){
        var value = JSON.stringify(val);
        window.localStorage.setItem(key,value);
    }

    // 获取本地存储
    var getStorage=function(key){
        var value = window.localStorage.getItem(key);
        if(value){
            return JSON.parse(value);
        }
        return null;
    }

    // 更新本地存储
    var putStorage=function(key,item,val){
            var value = window.localStorage.getItem(key);
            if(value){
                var json= JSON.parse(value);
                json[item]=val;

                value = JSON.stringify(json);
                window.localStorage.setItem(key,value);
            }
        }

    // 删除本地存储
    var removeStorage=function(key){
        window.localStorage.removeItem(key);
    }


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
        var user = this.getUser();
        var data = {
            method: option.method,
            mode: 'cors',
            headers: {
              'Authorization': user.token,
              'Content-Type': 'application/json'
            }
        }

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
                data.body = JSON.stringify(option.data);
            break;
        }

        fetch(option.url, data)
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

    // 登录
    exports.login = function(user,redirect){
        user.login=true;
        setStorage('user.token',user);
        if(redirect){
            setTimeout(function(){
                window.location.href='index.html';
            }, 3000);
        }
    }
    // 退出
    exports.logout = function(){
        removeStorage('user.token');
    }

    // 获取用户
    exports.getUser = function(){
        var user = getStorage('user.token') || {token:''};
        user.avatar = user.avatar || '/asset/img/icon_header.png';
        return user;
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


