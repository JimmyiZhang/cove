package plus.cove.infrastructure.test.utils;

import plus.cove.infrastructure.coordinate.BaseCoordinate;
import plus.cove.infrastructure.coordinate.CoordinateHelper;
import plus.cove.infrastructure.coordinate.DefaultCoordinate;
import org.junit.Test;
import org.springframework.util.Assert;

public class CoordinateHelperTest {
    @Test
    public void Wgs84ToGcj02() {
        BaseCoordinate coordinate = new DefaultCoordinate(39.1157883600, 117.7312592500);

        CoordinateHelper.Wgs84ToGcj02(coordinate);
        String gcj02 = String.format("Latitude: %.10f, Longitude: %.10f",
                coordinate.getLatitude(), coordinate.getLongitude());
        System.out.println(gcj02);

        Assert.isTrue(Math.abs(coordinate.getLatitude() - 39.11677) < 0.0001, "纬度");
        Assert.isTrue(Math.abs(coordinate.getLongitude() - 117.73756) < 0.0001, "经度");
    }
}
