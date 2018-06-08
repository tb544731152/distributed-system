package com.itmuch.cloud.study.myenum;

public enum  Code {
    SUCCESS("0000","上传成功"),
    FAIL("0001","上传失败"),
    PARAMETER_LOST("0002","参数丢失");
    private  String code;
    private String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
