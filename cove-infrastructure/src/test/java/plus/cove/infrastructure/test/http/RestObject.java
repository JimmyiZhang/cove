package plus.cove.infrastructure.test.http;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestObject {
    /**
     * 短整数
     */
    private Integer intValue;

    /**
     * 长整数
     */
    private Long longValue;

    /**
     * 日期数
     */
    private LocalDateTime dateValue;
}
