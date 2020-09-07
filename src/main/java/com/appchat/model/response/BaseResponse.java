package com.appchat.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int status;
    private String message;
    private Object data;

    private BaseResponse(){

    }
    public static BaseResponse createResponse(
            int status, String message
    ){
        BaseResponse response = new BaseResponse();
        response.status =status;
        response.message = message;
        return response;
    }
    public static BaseResponse createResponse(
            Object data
    ){
        BaseResponse response = new BaseResponse();
        response.data = data;
        response.status =1;
        response.message = "SUCCESS";
        return response;
    }

    public static BaseResponse createResponseRegister(Object register){
        BaseResponse response = new BaseResponse();
        response.status = 1;
        response.data = register;
        response.message = "SUCCESS";
        return response;
    }
}
