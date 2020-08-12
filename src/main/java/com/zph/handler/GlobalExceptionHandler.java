package com.zph.handler;

import com.zph.utils.Msg;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理参数异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({org.springframework.validation.BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Msg handleMethodArgumentNotValidException(Exception exception){
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = bindResult.getFieldErrors();
        for (FieldError error : list) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return Msg.message(400, "非法参数").add("validated", map);
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Msg handleHttpRequestMethodNotSupportedException(Exception exception){
        return Msg.message(405, "非法请求方式");
    }

    @ExceptionHandler({JwtException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Msg handleJwtException(Exception exception){
        return Msg.message(401, "非法请求").add("validated",exception.getMessage());
    }


}
