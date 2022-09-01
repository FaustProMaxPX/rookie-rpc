package server;

import com.bei.rookie_rpc.core.server.RpcServer;

public class ServerTest {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(new CalculateServiceImpl());
        server.serve();
    }
}
