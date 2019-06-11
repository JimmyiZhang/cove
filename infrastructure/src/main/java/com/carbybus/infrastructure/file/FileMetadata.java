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
    private Double latitude;
    private Double longitude;
    private String make;
    private String model;
    private String name;
    private String url;
    private LocalDateTime token;

    public boolean isValid() {
        return latitude != null && longitude != null;
    }
}
