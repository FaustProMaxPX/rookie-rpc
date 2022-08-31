package com.bei.rookie_rpc.IDL;

public enum CalMethod {
    ADD(0, "add"), MINUS(1, "minus");
    private Integer id;
    private String method;
    CalMethod(Integer id, String method) {
        this.id = id;
        this.method = method;
    }
}
