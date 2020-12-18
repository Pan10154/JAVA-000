package io.kimmking.rpcfx.api;

public interface RpcfxResolver<T> {

    Object resolve(String serviceClass);
    T resolve(Class<T> clazz);

}
