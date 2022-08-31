package com.bei.rookie_rpc.IDL;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CalculateRequest implements Serializable {
    private Long num1;
    private Long num2;
    private CalMethod method;
}
