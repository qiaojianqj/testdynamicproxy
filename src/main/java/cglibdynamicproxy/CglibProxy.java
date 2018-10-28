package cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @description: This is a usecase for cglib proxy
 * @author: Qiao.Jian
 * @create: 2018-09-20 11:17
 */
public class CglibProxy implements MethodInterceptor{
    private static Logger logger = LoggerFactory.getLogger ( CglibProxy.class );
    private static CglibProxy cglibProxy = new CglibProxy ();
    private CglibProxy() {}

    public static  CglibProxy getInstance() {
        return cglibProxy;
    }

    public <T> T getProxy(Class<T> clazz) {
       return (T) Enhancer.create ( clazz, this );
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.info ( "CglibProxy intercepting..." );
        return methodProxy.invokeSuper ( o, objects );
    }
}
