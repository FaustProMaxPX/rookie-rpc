package com.bei.rookie_rpc.core.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RpcResponseBody implements Serializable {
    private Object retObj;
}
