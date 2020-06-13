(function() {
    // ================坐标转换BEGIN================
    // 定义一些常量
    var x_PI = 3.14159265358979324 * 3000.0 / 180.0;
    var PI = 3.1415926535897932384626;
    var a = 6378245.0;
    var ee = 0.00669342162296594323;

    // 定义私有方法
    var transformlat = function transformlat(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        var ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return ret
    };

    var transformlng = function transformlng(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        var ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return ret
    };

    /**
     * 判断是否在国内，不在国内则不做偏移
     * @param lng
     * @param lat
     * @returns {boolean}
     */
    var out_of_china = function out_of_china(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        // 纬度3.86~53.55,经度73.66~135.05
        return !(lng > 73.66 && lng < 135.05 && lat > 3.86 && lat < 53.55);
    };

    /**
     * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
     * 即 百度 转 谷歌、高德
     * @param bd_lon
     * @param bd_lat
     * @returns {*[]}
     */
    var bd09togcj02 = function bd09togcj02(bd_lon, bd_lat) {
        var bd_lon = +bd_lon;
        var bd_lat = +bd_lat;
        var x = bd_lon - 0.0065;
        var y = bd_lat - 0.006;
        var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_PI);
        var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_PI);
        var gg_lng = z * Math.cos(theta);
        var gg_lat = z * Math.sin(theta);
        return [gg_lng, gg_lat]
    };

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
     * 即谷歌、高德 转 百度
     * @param lng
     * @param lat
     * @returns {*[]}
     */
    var gcj02tobd09 = function gcj02tobd09(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        var z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_PI);
        var theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_PI);
        var bd_lng = z * Math.cos(theta) + 0.0065;
        var bd_lat = z * Math.sin(theta) + 0.006;
        return [bd_lng, bd_lat]
    };

    /**
     * WGS84转GCj02
     * @param lng
     * @param lat
     * @returns {*[]}
     */
    var wgs84togcj02 = function wgs84togcj02(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        if (out_of_china(lng, lat)) {
            return [lng, lat]
        } else {
            var dlat = transformlat(lng - 105.0, lat - 35.0);
            var dlng = transformlng(lng - 105.0, lat - 35.0);
            var radlat = lat / 180.0 * PI;
            var magic = Math.sin(radlat);
            magic = 1 - ee * magic * magic;
            var sqrtmagic = Math.sqrt(magic);
            dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
            dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
            var mglat = lat + dlat;
            var mglng = lng + dlng;
            return [mglng, mglat]
        }
    };

    /**
     * GCJ02 转换为 WGS84
     * @param lng
     * @param lat
     * @returns {*[]}
     */
    var gcj02towgs84 = function gcj02towgs84(lng, lat) {
        var lat = +lat;
        var lng = +lng;
        if (out_of_china(lng, lat)) {
            return [lng, lat]
        } else {
            var dlat = transformlat(lng - 105.0, lat - 35.0);
            var dlng = transformlng(lng - 105.0, lat - 35.0);
            var radlat = lat / 180.0 * PI;
            var magic = Math.sin(radlat);
            magic = 1 - ee * magic * magic;
            var sqrtmagic = Math.sqrt(magic);
            dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
            dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
            var mglat = lat + dlat;
            var mglng = lng + dlng;
            return [lng * 2 - mglng, lat * 2 - mglat]
        }
    };
    // ================坐标转换END================

    var map;

    // 输出对象
    var exports = {};

    // 初始化地图
    exports.initMap = function(option) {
        map = new AMap.Map(option.id, {
            resizeEnable: true,
            zoom: 11,
        });
        AMap.plugin('AMap.ToolBar', function() {
            var toolbar = new AMap.ToolBar({
                offset: new AMap.Pixel(0, 80),
                position: 'LT'
            });
            map.addControl(toolbar)
        });
        AMap.plugin('AMap.Geolocation', function() {
            var geolocation = new AMap.Geolocation({
                enableHighAccuracy: true,
                timeout: 10000,
                showMarker: true,
                showCircle: false,
                zoomToAccuracy: true
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

        map.on('moveend', option.onMove);
        return map;
    };

    // 获取地图范围
    exports.getBounds = function() {
        var bounds = map.getBounds();
        var min = gcj02towgs84(bounds.southwest.lng, bounds.southwest.lat);
        var max = gcj02towgs84(bounds.northeast.lng, bounds.northeast.lat);

        return {
            minLatitude: min[1],
            minLongitude: min[0],
            maxLatitude: max[1],
            maxLongitude: max[0],
        }
    }

    exports.transformCoordinate = function(coordinate) {
        var coordinate = wgs84togcj02(coordinate.longitude, coordinate.latitude);
        coordinate.longitude = coordinate[0];
        coordinate.latitude = coordinate[1];
    }

    exports.addMarker = function(option) {
        var mrkContent = `<div class="marker"><img class="${option.className}" src="${option.icon}" /></div>`;
        var marker = new AMap.Marker({
            content: mrkContent,
            map: map,
            title: option.title,
            position: new AMap.LngLat(option.longitude, option.latitude),
        });
        marker.on('click', option.onClick);
    }

    exports.addWindow = function(option) {
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

    if (!window.cove) window.cove = {};
    window.cove.map = exports;
})();