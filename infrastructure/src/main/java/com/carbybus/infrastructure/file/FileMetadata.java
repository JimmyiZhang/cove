package com.carbybus.infrastructure.file;

import com.carbybus.infrastructure.coordinate.BaseCoordinate;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文件元信息
 *
 * @author jimmy.zhang
 * @date 2019-06-04
 */
@Data
public class FileMetadata implements BaseCoordinate {
    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 制造商
     */
    private String make;

    /**
     * 型号
     */
    private String model;

    /**
     * 名字
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 拍摄时间
     */
    private LocalDateTime token;

    /**
     * 判断经纬是否为空
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    public boolean hasCoordinate() {
        return latitude != null && longitude != null;
    }
}
