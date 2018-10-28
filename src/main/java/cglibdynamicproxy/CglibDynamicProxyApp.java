package cglibdynamicproxy;

import bo.Bird;

/**
 * @description: This is main to test cglib
 * @author: Qiao.Jian
 * @create: 2018-09-20 11:23
 */
public class CglibDynamicProxyApp {
    public static void main(String[] args) {
        Bird bird = CglibProxy.getInstance ().getProxy ( Bird.class );
        bird.fly ();
    }
}
