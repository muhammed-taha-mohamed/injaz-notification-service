package com.injaz_notification_service.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePayload {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Map<?,?> content;
    private Pageable pageable;
    private Map<?,?> error;
    private Long totalElements;
    private Integer totalPages;
}