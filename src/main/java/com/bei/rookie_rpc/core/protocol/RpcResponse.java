package com.bei.rookie_rpc.core.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RpcResponse implements Serializable {
    private String header;
    private byte[] retObj;
}
