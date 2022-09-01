package com.bei.rookie_rpc.core.server;

import com.bei.rookie_rpc.core.body.RpcRequestBody;
import com.bei.rookie_rpc.core.body.RpcResponseBody;
import com.bei.rookie_rpc.core.protocol.RpcRequest;
import com.bei.rookie_rpc.core.protocol.RpcResponse;
import com.bei.rookie_rpc.core.utils.SerializeUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class RpcServerWorker implements Runnable{

    private Socket socket;
    private Map<String, Object> registeredService;

    public RpcServerWorker(Socket socket, Map<String, Object> registeredService) {
        this.socket = socket;
        this.registeredService = registeredService;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest req = (RpcRequest) ois.readObject();
            RpcRequestBody body = (RpcRequestBody) SerializeUtils.deSerialize(req.getBody());
            Object impl = registeredService.get(body.getInterfaceName());
            if (impl == null) {
                return;
            }
            Method method = impl.getClass().getMethod(body.getMethodName(), body.getParameterTypes());
            Object ret = method.invoke(impl, body.getParameters());
            RpcResponseBody respBody = new RpcResponseBody(ret);
            byte[] byteBody = SerializeUtils.serialize(respBody);
            RpcResponse rpcResponse = new RpcResponse("v1.0", byteBody);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rpcResponse);
            oos.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
