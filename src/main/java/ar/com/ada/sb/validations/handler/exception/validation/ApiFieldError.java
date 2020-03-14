package ar.com.ada.sb.validations.handler.exception.validation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"field", "code", "message"})
public class ApiFieldError {
    private String field;
    private String code;
    private String message;

    public ApiFieldError(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiFieldError{" +
                "field='" + field +
                ", code='" + code +
                ", message='" + message;
    }
}
