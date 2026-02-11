//package com.dtt.icp.responseentity;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class ApiResponseW3c {
//
//    @JsonProperty("Success")
//    private boolean Success;
//
//    @JsonProperty("Message")
//    private String Message;
//
//    @JsonProperty("ErrorCode")
//    private int ErrorCode;
//
//    @JsonProperty("ErrorMessage")
//    private String ErrorMessage;
//
//    @JsonProperty("Result")
//    private Object Result;
//
//
//    public boolean isSuccess() {
//        return Success;
//    }
//
//    public void setSuccess(boolean success) {
//        Success = success;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String message) {
//        Message = message;
//    }
//
//    public int getErrorCode() {
//        return ErrorCode;
//    }
//
//    public void setErrorCode(int errorCode) {
//        ErrorCode = errorCode;
//    }
//
//    public String getErrorMessage() {
//        return ErrorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        ErrorMessage = errorMessage;
//    }
//
//    public Object getResult() {
//        return Result;
//    }
//
//    public void setResult(Object result) {
//        Result = result;
//    }
//
//    @Override
//    public String toString() {
//        return "ApiResponseW3c{" +
//                "Success=" + Success +
//                ", Message='" + Message + '\'' +
//                ", ErrorCode=" + ErrorCode +
//                ", ErrorMessage='" + ErrorMessage + '\'' +
//                ", Result=" + Result +
//                '}';
//    }
//}



package com.dtt.simulations.POA.responseentity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponseW3c {

    @JsonProperty("Success")
    private boolean Success;

    @JsonProperty("Message")
    private String Message;

    @JsonProperty("ErrorCode")
    private int ErrorCode;

    @JsonProperty("ErrorMessage")
    private String ErrorMessage;

    @JsonProperty("Result")
    private Object Result;

    // ✅ No-arg constructor
    public ApiResponseW3c() {
    }

    // Getters and Setters
    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }

    @Override
    public String toString() {
        return "ApiResponseW3c{" +
                "Success=" + Success +
                ", Message='" + Message + '\'' +
                ", ErrorCode=" + ErrorCode +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                ", Result=" + Result +
                '}';
    }
}

