package jdkdynamicproxy;

import bo.Bird;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: This is self defined invocation handler
 * @author: Qiao.Jian
 * @create: 2018-09-19 23:33
 */
public class FlyTimeJdkInvocationHandler implements JdkInvocationHandler {
    private Logger logger = LoggerFactory.getLogger ( FlyTimeJdkInvocationHandler.class );

    private Bird bird;

    public FlyTimeJdkInvocationHandler(Bird bird) {
        this.bird = bird;
    }

    //在此方法里实现我们真正的代理逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        long start = System.currentTimeMillis ();

        try {
            method.invoke ( bird, new Object[]{} );
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        }

        long end = System.currentTimeMillis ();

        logger.info ( "Fly time = {}", end - start );
        return proxy;
    }
}
