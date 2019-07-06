package com.carbybus.infrastructure.utils;

import com.carbybus.infrastructure.coordinate.BaseCoordinate;
import com.carbybus.infrastructure.coordinate.CoordinateUtils;
import com.carbybus.infrastructure.coordinate.DefaultCoordinate;
import org.junit.Test;
import org.springframework.util.Assert;

public class CoordinateUtilsTest {
    @Test
    public void Wgs84ToGcj02() {
        BaseCoordinate coordinate = new DefaultCoordinate(39.1157883600, 117.7312592500);

        CoordinateUtils.Wgs84ToGcj02(coordinate);
        String gcj02 = String.format("Latitude: %.10f, Longitude: %.10f",
                coordinate.getLatitude(), coordinate.getLongitude());
        System.out.println(gcj02);

        Assert.isTrue(Math.abs(coordinate.getLatitude() - 39.11677) < 0.0001, "纬度");
        Assert.isTrue(Math.abs(coordinate.getLongitude() - 117.73756) < 0.0001, "经度");
    }
}
