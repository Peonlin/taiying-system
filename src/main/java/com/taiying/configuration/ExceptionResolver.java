package com.taiying.configuration;

import com.taiying.common.entity.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception e) {
        ResponseEntity r = new ResponseEntity();
        r.setCode("fail");
        r.setMsg(e.getMessage());
        return r;
    }
}
