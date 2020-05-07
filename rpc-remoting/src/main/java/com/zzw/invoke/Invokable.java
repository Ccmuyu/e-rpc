package com.zzw.invoke;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public interface Invokable<I, O> {

    /**
     * 调用拦截
     *
     * @param i
     * @return
     */
    O invoke(I i);
}
