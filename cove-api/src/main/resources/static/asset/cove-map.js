(function() {
    var map;

    // 输出对象
    var exports = {};

    // 初始化地图
    exports.initMap = function(id) {
        map = new AMap.Map(id, {
            resizeEnable: true,
            zoom: 11,
        });
        AMap.plugin('AMap.ToolBar', function() {
            var toolbar = new AMap.ToolBar({
                offset:new AMap.Pixel(0,80),
                position:'LT'
            });
            map.addControl(toolbar)
        });
        AMap.plugin('AMap.Geolocation', function() {
            geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,
                timeout: 10000,
                showMarker: true,
                showCircle: false,
            });
            geolocation.getCurrentPosition();
            AMap.event.addListener(geolocation, 'complete', function(result) {
                console.log('定位成功');
                map.setCenter(result.position);
            });
            AMap.event.addListener(geolocation, 'error', function(result) {
                console.log('定位失败');
            });
        });

        return map;
    };

    // 获取地图范围
    exports.getBounds = function() {
        var bounds = map.getBounds();
        var min = coordtransform.gcj02towgs84(bounds.southwest.lng, bounds.southwest.lat);
        var max = coordtransform.gcj02towgs84(bounds.northeast.lng, bounds.northeast.lat);

        return {
            minLatitude: min[1],
            minLongitude: min[0],
            maxLatitude: max[1],
            maxLongitude: max[0],
        }
    }

    exports.transformCoordinate = function(coordinate){
        var coordinate = coordtransform.wgs84togcj02(coordinate.longitude, coordinate.latitude);
        coordinate.longitude = coordinate[0];
        coordinate.latitude = coordinate[1];
    }

    exports.addMarker=function(option){
        var mrkContent = `<div class="marker"><img class="${option.className}" src="${option.icon}" /></div>`;
        var marker = new AMap.Marker({
            content: mrkContent,
            map: map,
            title: option.title,
            position: new AMap.LngLat(option.longitude, option.latitude),
        });
        marker.on('click', option.onClick);
    }

    exports.addWindow=function(option){
        // 信息窗体内容
        var content = `<div><img style='width:200px;height:100%;'><br><div>${option.description}</div></div>`;
        // 创建窗体实例
        var info = new AMap.InfoWindow({
            content: content,
            anchor: 'bottom-center',
            offset: new AMap.Pixel(12, -24)
        });

        // info.open(map, [item.longitude, item.latitude]);
    }

    // 移动
    exports.onMove = function(fn){
        map.on('moveend', fn);
    }

    if(!window.cove) window.cove={};
    window.cove.map = exports;
})();


