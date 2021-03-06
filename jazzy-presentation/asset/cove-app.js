(function() {
    var api_url = "http://101.200.53.244:9080";

    // ==================本地工具BEGIN====================
    // 保存本地存储
    var setStorage = function(key, val) {
        var value = JSON.stringify(val);
        window.localStorage.setItem(key, value);
    }

    // 获取本地存储
    var getStorage = function(key) {
        var value = window.localStorage.getItem(key);
        if (value) {
            return JSON.parse(value);
        }
        return null;
    }

    // 更新本地存储
    var putStorage = function(key, item, val) {
        var value = window.localStorage.getItem(key);
        if (value) {
            var json = JSON.parse(value);
            json[item] = val;

            value = JSON.stringify(json);
            window.localStorage.setItem(key, value);
        }
    }

    // 删除本地存储
    var removeStorage = function(key) {
        window.localStorage.removeItem(key);
    }
    // ==================本地工具END======================


    // ==================公共工具START======================
    var utils = {};
    utils.getUrlQuery = function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    utils.getRandomNumber = function(min, max) {
        return Math.floor(Math.random() * (max - min) + min);;
    }
    // =================公共工具END===================


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

                'Content-Type': 'application/json'
            }
        }

        option.data = option.data || {};
        option.method = option.method || 'GET';

        switch (option.method) {
            case 'GET':
                if (option.url.indexOf('?') == -1) {
                    option.url = option.url + '?';
                }
                Object.keys(option.data).forEach(function(key) {
                    option.url = option.url + key + "=" + option.data[key] + "&";
                });
                option.url = option.url.substr(0, option.url.length - 1);
                break;
            case 'POST':
                data.body = JSON.stringify(option.data);
                break;
        }

        fetch(api_url + option.url, data)
            .then(function(response) {
                response.json().then(function(json) {
                    if (option.onSuccess) {
                        option.onSuccess(json);
                    }
                });
            })
            .catch(function(error) {
                if (option.onError) {
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
    exports.delay = function(option) {
        setTimeout(option.onAction, delay * 1000);
    }

    // 登录
    exports.login = function(user, redirect) {
        user.login = true;
        setStorage('user.token', user);
        if (redirect) {
            setTimeout(function() {
                window.location.href = 'index.html';
            }, 3000);
        }
    }

    // 退出
    exports.logout = function() {
        removeStorage('user.token');
    }

    // 获取用户
    exports.getUser = function() {
        var user = getStorage('user.token') || { token: '' };
        user.avatar = user.avatar || '/asset/img/icon_header.png';
        return user;
    }

    // 去登录
    exports.toLogin = function() {
        window.location.href = 'login.html';
    }

    // 去首页
    exports.toHome = function() {
        window.location.href = 'index.html';
    }

    if (!window.cove) window.cove = {};
    window.cove.app = exports;
    window.cove.utils = utils;
})();