(function() {
    var uppy;
    var viewer;
    var user;

    // 输出对象
    var exports = {};

    // 初始化地图
    exports.initUpload = function(option) {
        // 上传
        uppy = Uppy.Core({
                debug: true,
                autoProceed: false,
                restrictions: {
                    maxFileSize: 10000000,
                    maxNumberOfFiles: 10,
                    allowedFileTypes: ['image/*']
                },
                locale: Uppy.locales.zh_CN
            })
            .use(Uppy.Dashboard, {
                inline: true,
                target: '#'+option.id,
                replaceTargetContent: true,
                showProgressDetails: true,
                note: '只允许上传图片, 最多10个文件，最大10M',
                height: 470,
                metaFields: [{
                    id: 'name',
                    name: '名称',
                    placeholder: '文件名称'
                }],
                browserBackButtonClose: true
            })
            .use(Uppy.XHRUpload, {
                fieldName: 'file',
                headers: {
                    'Authorization': `Bearer ${cove.app.getUser().token}`
                },
                endpoint: option.endpoint
            });

        uppy.on("complete", (result) => {
            console.log("upload complete")
        });

        uppy.on("upload-success", (file, response) => {
            console.log('upload success');
            if(option.onSuccess){
                option.onSuccess(response.body);
            }
        });
        uppy.on("upload-error", (file, error, response) => {
            console.log("upload error");
        });
    };

    // 清除
    exports.clearUpload = function() {
        uppy.reset();
    }

    // 获取缩略图
    exports.getThumbnail  = function(url){
        var dot = url.lastIndexOf('.');
        return 'uploads/'+url.substring(0,dot)+"_t"+url.substring(dot);
    }

    // 获取装饰图
    exports.getOrnament = function(url){
        return "file/download?name="+url.replace('\\', '/');
    }

    // 初始化显示
    exports.initViewer = function(url) {
        var image = new Image();
    	image.src = url;

        viewer = new Viewer(image, {
          navbar: false,
          toolbar: {
        	zoomIn: 4,
        	zoomOut: 4,
        	oneToOne: 4,
        	reset: 4,
        	prev: 0,
        	play: {
        	  show: 4,
        	  size: 'large',
        	},
        	next: 0,
        	rotateLeft: 4,
        	rotateRight: 4,
        	flipHorizontal: 4,
        	flipVertical: 4,
          },
          hidden: function () {
        	viewer.destroy();
          },
        });
        return viewer;
    }

    if(!window.cove) window.cove={};
    window.cove.file = exports;
})();


