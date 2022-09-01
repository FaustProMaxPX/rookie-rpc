package com.bei.rookie_rpc.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class RpcServer {

    private Map<String, Object> registeredService;

    private ThreadPoolExecutor pool;

    public RpcServer() {
        registeredService = new HashMap<>();
        int corePoolSize = 5;
        int maxPoolSize = 10;
        int aliveTime = 30;
        pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public void register(Object impl) {
        registeredService.put(impl.getClass().getInterfaces()[0].getName(), impl);
    }

    public void serve() {
        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("server start...");
            Socket handleSocket;
            while ((handleSocket = serverSocket.accept()) != null) {
                System.out.println("get a request from: " + handleSocket.getInetAddress());
                pool.execute(new RpcServerWorker(handleSocket, registeredService));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
