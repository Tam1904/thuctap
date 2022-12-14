package com.example.demo.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseData {

    private static final String ERRORS_KEY = "errors";

    private  String message;

    private  int code;

    private Map<String, Object> data = new HashMap<>();

    public  ResponseData putDataValue(String key, Object value){
        data.put(key, value);
        return this;
    }

    private ResponseData(int code, String message){
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok(){
        return new ResponseData(200, "ok");
    }

    public static ResponseData notFound(){
        return  new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "Customer Error");
    }


}
