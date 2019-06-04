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
    private String name;
    private String url;
    private String type;
    private Long size;

    @JsonIgnore
    private Path fullPath;
}
