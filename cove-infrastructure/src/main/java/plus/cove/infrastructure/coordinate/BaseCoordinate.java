package plus.cove.infrastructure.coordinate;

/**
 * 坐标接口
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
public interface BaseCoordinate {
    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);
}
