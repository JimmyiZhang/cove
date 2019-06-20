package com.carbybus.cove.domain.view;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.time.LocalDateTime;


/**
 * 照片创建输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
public class StoryCreateInput {
    @NotNull(message = "图片不能为空")
    private String url;

    @NotNull(message = "拍照时间不能为空")
    private LocalDateTime takeTime;

    @NotNull(message = "纬度不能为空")
    @Min(value = -90, message = "纬度必须大于-90")
    @Max(value = 90, message = "纬度必须小于90")
    private Double latitude;

    @NotNull(message = "经度不能为空")
    @Min(value = -128, message = "经度必须大于-128")
    @Max(value = 128, message = "经度必须小于128")
    private Double longitude;

    @NotEmpty(message = "描述不能为空")
    @Size(min = 1, max = 128, message = "描述最多128个字符")
    private String description;

    @NotEmpty(message = "主题不能为空")
    @Size(min = 1, max = 128, message = "主题最多128个字符")
    private String subject;
}
