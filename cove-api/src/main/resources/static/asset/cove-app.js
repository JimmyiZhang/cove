(function() {
    var uppy;

    // 输出对象
    var exports = {};

    // 发送数据
    /* 参数说明
    * option = {
    *    url:'',
    *    method: '',
    *    data:{},
    *    onSuccess:function(json){}
    * }
    */
    exports.send = function(option) {
        switch(option.method){
            case 'GET':
                if(option.url.indexOf('?')==-1){
                    option.url=option.url+'?';
                }
                Object.keys(option.data).forEach(function(key) {
                    option.url = option.url + key + "=" + option.data[key] + "&";
                });
            break;
        }

        fetch(option.url, {mode: 'cors'})
            .then(function(response) {
                response.json().then(function(json) {
                    option.onSuccess(json);
                });
            });
    };

    if(!window.cove) window.cove={};
    window.cove.app = exports;
})();


