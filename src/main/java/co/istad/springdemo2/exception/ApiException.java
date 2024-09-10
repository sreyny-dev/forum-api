package co.istad.springdemo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//To catch all types of error
@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<Map<String, String>> errorDetail =new ArrayList<>();

        e.getFieldErrors()
                .forEach(fieldError->{
                    Map<String, String> errorDetailMap=new HashMap<>();
                    errorDetailMap.put("field", fieldError.getField());
                    errorDetailMap.put("reason", fieldError.getDefaultMessage());
                    errorDetail.add(errorDetailMap);
                }
                );
        ErrorDetailResponse<?> errorDetailResponse=ErrorDetailResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .description(errorDetail)
                .build();
        return ErrorResponse.builder()
                .error(errorDetailResponse)
                .build();
    }
}
