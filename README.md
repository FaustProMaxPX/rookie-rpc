# 简易RPC实现
一个RPC框架的极简实现版本

远程调用的基本过程如下：
1. 客户端与服务端先利用IDL生成对应的静态库
2. 客户端发起请求调用之后，将请求的内容(接口名，方法名，参数等等)编码
3. 客户端将编码结果放入请求体当中，在请求头中包含服务端需要知道的信息，以上的所有操作要符合使用的RPC协议。
4. 请求交给网络库进行传输
5. 服务端进行相应的反向操作，解析出需要执行的方法。生成返回值后原路返回给客户端。
   ![RPC基本架构图](https://raw.githubusercontent.com/FaustProMaxPX/pic_repository/main/rpc/2022-08-31%2015-37-55%20%E7%9A%84%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE.png)