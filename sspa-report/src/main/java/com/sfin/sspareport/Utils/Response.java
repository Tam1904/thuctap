package com.sfin.sspareport.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {

    private Map<String, Object> data = new HashMap<>();

    public Response putDataValue(String key, Object value){
        data.put(key, value);
        return this;
    }
}
