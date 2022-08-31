package com.bei.rookie_rpc.IDL;

public interface CalculateService {
    CalculateResponse add(CalculateRequest req);
    CalculateResponse minus(CalculateRequest req);
}
