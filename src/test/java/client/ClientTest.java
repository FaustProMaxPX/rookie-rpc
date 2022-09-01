package client;

import com.bei.rookie_rpc.IDL.CalMethod;
import com.bei.rookie_rpc.IDL.CalculateRequest;
import com.bei.rookie_rpc.IDL.CalculateResponse;
import com.bei.rookie_rpc.IDL.CalculateService;
import com.bei.rookie_rpc.core.client.RpcClientProxy;

public class ClientTest {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        CalculateService service = proxy.getService(CalculateService.class);
        CalculateRequest add = new CalculateRequest(1L, 2L, CalMethod.ADD);
        CalculateResponse resp = service.add(add);
        System.out.println(resp.getResult());
    }
}
