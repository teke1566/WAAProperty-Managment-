package edu.miu.waa.project.backend.domain.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class HttpResponse {
    private String message;
    private HttpStatus status;
}
