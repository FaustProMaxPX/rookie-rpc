package com.bei.rookie_rpc.core.client;

import com.bei.rookie_rpc.core.body.RpcRequestBody;
import com.bei.rookie_rpc.core.body.RpcResponseBody;
import com.bei.rookie_rpc.core.protocol.RpcRequest;
import com.bei.rookie_rpc.core.protocol.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class RpcClientProxy implements InvocationHandler {

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequestBody rpcRequestBody = RpcRequestBody.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .build();
        // 将对象序列化为字节流 [客户端编码]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rpcRequestBody);
        RpcRequest rpcRequest = new RpcRequest("v1.0", baos.toByteArray());
        // 客户端发送请求
        RpcResponse resp = RpcClientTransfer.request(rpcRequest);
        if (resp == null) return null;
        // 客户端接受到请求，并反序列化结果
        ByteArrayInputStream bais = new ByteArrayInputStream(rpcRequest.getBody());
        ObjectInputStream ois = new ObjectInputStream(bais);
        RpcResponseBody body = (RpcResponseBody) ois.readObject();
        return body.getRetObj();

    }
}
