package com.bei.rookie_rpc.core.client;

import com.bei.rookie_rpc.core.body.RpcResponseBody;
import com.bei.rookie_rpc.core.protocol.RpcRequest;
import com.bei.rookie_rpc.core.protocol.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class RpcClientTransfer {

    public static RpcResponse request(RpcRequest req) {
        try (Socket socket = new Socket("localhost", 9000)){
            // 获取输出流，将请求发送给服务器
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(req);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // 读取来自服务端的响应
            RpcResponse resp = (RpcResponse) ois.readObject();
            return resp;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
