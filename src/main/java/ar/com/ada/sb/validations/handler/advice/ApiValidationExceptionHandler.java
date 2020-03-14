package ar.com.ada.sb.validations.handler.advice;

import ar.com.ada.sb.validations.handler.exception.validation.ApiErrorsResponseBody;
import ar.com.ada.sb.validations.handler.exception.validation.ApiFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        //se obtiene la lista de todos los errores ocurridos
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        //en base a la lista anterior se genera una nueva con objetos ApiFieldError que
        //describe la informacion de los errores
        List<ApiFieldError> apiFieldErrors = fieldErrors
                .stream()
                //por cada objeto FieldError se crea uno de tipo ApiFieldError con
                //la informacion necesaria
                .map(fieldError -> new ApiFieldError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage())
                )
                //se convierte el mapa a una lista List<ApiFieldError>
                .collect(Collectors.toList());

        //se crea un objeto ApiErrorsResposeBody que contiene la lista ApiFieldErrors
        //y la informacion de tiempo, status code y status response
        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody(
                HttpStatus.BAD_REQUEST.value();
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                apiFieldErrors);
        //se retorna el response final de los errores
        return ResponseEntity
                .badRequest()
                .body(apiErrorsResponseBody);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //dividimos el mensaje por los saltos de linea(/n) y lo almacenamos en una lista de String
        List<String> exMessage
    }
}
