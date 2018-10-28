package dynamicproxy;

import bo.Bird;
import bo.Flyable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description: This is main class for dynamic timeproxy
 * @author: Qiao.Jian
 * @create: 2018-09-19 22:38
 */
public class DynamicProxyApp {
    public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InterruptedException {

        ProxyGen.newProxyInstance ();
    }

}
