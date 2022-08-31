package com.bei.rookie_rpc.IDL;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CalculateResponse implements Serializable {
    private Long result;
}
