package ar.com.ada.sb.validations.handler.exception.validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"timestamp", "status", "error", "errors"})
public class ApiErrorsResponseBody {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Timestamp timestamp;
    private Integer status;
    private String error;
    private List<ApiFieldError> errors;

    public ApiErrorsResponseBody(Integer status, String error, List<ApiFieldError> errors) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.error = error;
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiErrorsResponseBody{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error +
                ", errors=" + errors;
    }
}
