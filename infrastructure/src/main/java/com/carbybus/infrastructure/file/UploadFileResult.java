package com.carbybus.infrastructure.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
