package plus.cove.infrastructure.coordinate;

/**
 * 坐标工具
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public class CoordinateHelper {
    private static final double PI = 3.14159265358979324D;

    /**
     * WGS84转GCJ02坐标
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static void Wgs84ToGcj02(BaseCoordinate baseCoordinate) {
        double[] deltas = delta(baseCoordinate.getLongitude(), baseCoordinate.getLatitude());
        double longitude = deltas[0] + baseCoordinate.getLongitude();
        double latitude = deltas[1] + baseCoordinate.getLatitude();

        baseCoordinate.setLatitude(latitude);
        baseCoordinate.setLongitude(longitude);
    }

    /**
     * GCJ02转WGS84
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static void Gcj02ToWgs84(BaseCoordinate baseCoordinate) {
        double[] deltas = delta(baseCoordinate.getLongitude(), baseCoordinate.getLatitude());
        double longitude = baseCoordinate.getLongitude() - deltas[0];
        double latitude = baseCoordinate.getLatitude() - deltas[1];

        baseCoordinate.setLatitude(latitude);
        baseCoordinate.setLongitude(longitude);

    }

    /**
     * 计算坐标距离
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-04
     */
    public static double distance(BaseCoordinate baseCoordinateA, BaseCoordinate baseCoordinateB) {
        int earthR = 6371000;
        double x = Math.cos(baseCoordinateA.getLatitude() * PI / 180.) *
                Math.cos(baseCoordinateB.getLatitude() * PI / 180.) *
                Math.cos((baseCoordinateA.getLongitude() - baseCoordinateB.getLongitude()) * PI / 180);
        double y = Math.sin(baseCoordinateA.getLatitude() * PI / 180.) *
                Math.sin(baseCoordinateB.getLatitude() * PI / 180.);

        double s = x + y;
        if (s > 1) {
            s = 1;
        }
        if (s < -1) {
            s = -1;
        }
        double alpha = Math.acos(s);
        double distance = alpha * earthR;
        return distance;
    }

    private static double[] delta(double lng, double lat) {
        // a: 卫星椭球坐标投影到平面地图坐标系的投影因子。
        double a = 6378245.0;
        // ee: 椭球的偏心率。
        double ee = 0.00669342162296594323;
        double dLat = transformLat(lng - 105.0, lat - 35.0);
        double dLon = transformLon(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * PI);

        return new double[]{dLon, dLat};
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }
}