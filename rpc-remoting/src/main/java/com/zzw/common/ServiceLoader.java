package com.zzw.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * e-rpc 的SPI
 * 采用服务发现的方式，预留扩展点
 * <p>
 * 拟支持的扩展：<br/>
 * 序列化方式、
 * 通信协议
 *
 * </p>
 *
 * @Author zhenwei.wang
 * @see Type 待加载的实例
 */
public class ServiceLoader<Type> {

    public static final String SERVICE_DIR = "META_INF/e-rpc/services";

    //缓存所有service接口
    public static final Map<Class<?>, ServiceLoader<?>> SERVICE_CACHE;

    //允许设置默认实现
    public static final Map<String/*class full name */, Object> SERVICE_INSTANCE_CACHE;
    //缓存service实例名称（全路径）及其class对象
    public static final Map<String/*class full name */, Class<?>> SERVICE_NAME_CACHE;


    static {
        SERVICE_CACHE = new ConcurrentHashMap<>();
        SERVICE_INSTANCE_CACHE = new ConcurrentHashMap<>();
        SERVICE_NAME_CACHE = new ConcurrentHashMap<>();
    }

    // none static区
    // 每个接口都拥有的实例列表
    private final Class<Type> type;
    private final ClassLoader classLoader;
    private final Map<String, Type> typeInstances = new LinkedHashMap<>();//允许多实例存在，适配不同场景

    private ServiceLoader(Class<Type> type, ClassLoader classLoader) {
        if (type == null) {
            throw new NullPointerException("Service interface cannot be null");
        }
        this.type = type;
        this.classLoader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        SERVICE_CACHE.putIfAbsent(type, this);
    }

    public ServiceLoader(Class<Type> type) {
        this.type = type;
        classLoader = ClassLoader.getSystemClassLoader();
        SERVICE_CACHE.putIfAbsent(type, this);
    }

    public static <Type> ServiceLoader<Type> load(Class<Type> typeClass, ClassLoader classLoader) {
        return new ServiceLoader<>(typeClass, classLoader);
    }

    public static <Type> ServiceLoader<Type> load(Class<Type> typeClass) {
        if (typeClass == null) {
            throw new NullPointerException("type class cannot be null");
        }
        ServiceLoader<?> refer = SERVICE_CACHE.get(typeClass);
        if (refer == null) {
            refer = new ServiceLoader<>(typeClass, Thread.currentThread().getContextClassLoader());
            SERVICE_CACHE.putIfAbsent(typeClass, refer);
            SERVICE_NAME_CACHE.putIfAbsent(typeClass.getName(), typeClass);
        }
        return (ServiceLoader<Type>) refer;
    }


    /**
     * 根据className，获取该类实例
     *
     * @param className
     * @return
     */
    public Type getServiceInstance(String className) {
        if (className == null || className.isEmpty()) {
            throw new NullPointerException(String.format("%s is null", className));
        }
        Object cache = SERVICE_INSTANCE_CACHE.get(className);
        if (cache == null) {
            Type obj = instantiate(className);
            SERVICE_INSTANCE_CACHE.putIfAbsent(className, obj);
            return obj;
        }
        return (Type) cache;
    }


    //TODO 支持动态加载扩展，可在运行动态增添、替换接口和实现类
    private Class<?> getServiceType(String className) {
        ServiceLoader<?> serviceLoader = SERVICE_CACHE.get(className);
        if (serviceLoader == null) {
//            serviceLoader = new ServiceLoader<>();
        }
        return null;
    }

    //instantiate for given class
    private Type instantiate(String className) {
        Class<?> aClass = SERVICE_NAME_CACHE.get(className);
        if (aClass == null) {
        }
        return instantiate(aClass);
    }

    public Type instantiate(Class<?> aClass) {
        if (aClass == null) {
            throw new NullPointerException(aClass.getName() + "class is null");
        }
        try {
            Object instance = aClass.newInstance();
            SERVICE_INSTANCE_CACHE.putIfAbsent(aClass.getName(), instance);
            Type value = (Type) instance;
            typeInstances.putIfAbsent(aClass.getName(), value);
            return value;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new NullPointerException(String.format("instantiate %s class failed.", aClass.getName()));
        }
    }


    //允许多实现，加载执行class的所有实例
    private void loadFile(String dir) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(dir + type.getName());
            while (resources.hasMoreElements()) {//实例列表加载
                URL url = resources.nextElement();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        try {
                            Class<?> serviceClass = Class.forName(line);//实例class
                            SERVICE_NAME_CACHE.putIfAbsent(type.getName(), serviceClass);
                            instantiate(serviceClass);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(String.format("%s with [%s] not found.", dir, line), e);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("load file failed for : " + dir, e);
        }
    }

}
