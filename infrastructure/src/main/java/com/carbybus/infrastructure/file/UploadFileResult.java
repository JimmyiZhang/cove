package com.carbybus.infrastructure.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

/**
 * 上传文件结果
 *
 * @author jimmy.zhang
 * @date 2019-05-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResult {
    /**
     * 名字
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 类型
     */
    private String type;

    /**
     * 大小
     */
    private Long size;

    /**
     * 全路径
     */
    @JsonIgnore
    private Path fullPath;
}
