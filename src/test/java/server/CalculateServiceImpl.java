package server;

import com.bei.rookie_rpc.IDL.CalculateRequest;
import com.bei.rookie_rpc.IDL.CalculateResponse;
import com.bei.rookie_rpc.IDL.CalculateService;

public class CalculateServiceImpl implements CalculateService {
    @Override
    public CalculateResponse add(CalculateRequest req) {
        return new CalculateResponse(req.getNum1() + req.getNum2());
    }

    @Override
    public CalculateResponse minus(CalculateRequest req) {
        return new CalculateResponse(req.getNum1() - req.getNum2());
    }
}
