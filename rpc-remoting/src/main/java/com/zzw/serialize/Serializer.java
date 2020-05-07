package com.zzw.serialize;


/**
 * @Date 2020-04-21
 * @Author zhenwei.wang
 * Serializer
 * define the basic method with serialize and deserialize
 */
public interface Serializer {


    /**
     * basic serialize method
     *
     * @param obj the obj to serialize
     * @param <T> generic type
     * @return the byte array with default Serialization mode
     */
    <T> byte[] serialize(T obj);

    /**
     * deserialize method
     *
     * @param data
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> tClass);
}
