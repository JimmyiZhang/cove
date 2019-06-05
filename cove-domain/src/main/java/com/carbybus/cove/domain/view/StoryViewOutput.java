package com.carbybus.cove.domain.view;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 照片创建输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
public class StoryViewOutput {
    private String name;
    private String url;
    private LocalDateTime takeTime;
    private Double latitude;
    private Double longitude;
    private String description;
    private String subject;
}
