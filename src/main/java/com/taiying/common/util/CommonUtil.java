package com.taiying.common.util;

import com.taiying.common.entity.ResponseEntity;

public class CommonUtil {

    public static ResponseEntity getSuccessResponse() {
        ResponseEntity r = new ResponseEntity();
        r.setCode("success");
        return r;
    }
}
