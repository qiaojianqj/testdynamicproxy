package jdkdynamicproxy;

import java.lang.reflect.Method;

/**
 * @description: This interface is analog to jdk JdkInvocationHandler
 * @author: Qiao.Jian
 * @create: 2018-09-20 10:09
 */
public interface JdkInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args);
}
