package jdkdynamicproxy;

import bo.Bird;
import bo.Flyable;

/**
 * @description: This is main class for jdk proxy
 *
 * 使用动态代理使得我们居然可以在不改变源码的情况下，直接在方法中插入自定义逻辑。
 * 这有点不太符合我们的一条线走到底的编程逻辑，这种编程模型有一个专业名称叫 AOP。所谓的AOP，就像刀一样，抓住时机，趁机插入。
 *
 * 基于这样一种动态特性，我们可以用它做很多事情，例如：
 * 事务提交或回退
 * 权限管理
 * 日志记录
 * ... ...
 *
 * JDK 动态代理局限：
 * 1. 要求被代理类必须实现一个接口
 * 2. 使用反射影响性能
 *
 * CGLIB 动态代理：
 * 1. 只需要被代理类不是标记为final即可（因为CGLIB基于继承实现代理）
 * 2. 使用ASM库直接对class文件进行修改，动态生成class文件
 *
 * @author: Qiao.Jian
 * @create: 2018-09-19 23:31
 */
public class JdkDynamicProxyApp {
    public static void main(String[] args) throws Exception {
        //此时，已经不需要修改newProxyInstance源码，就可以实现对任意对象对的代理
        //JDK 代理的三个步骤:
        //1. Proxy->newProxyInstance(infs, handler) 用于生成代理对象
        //2. InvocationHandler：实现这个接口主要用于自定义代理逻辑处理
        //3. 为了完成对被代理对象的方法拦截，我们需要在实现InvocationHandler接口的对象中传入被代理对象实例
       Flyable proxyFlyable = (Flyable) JdkProxyJavaFileGen.newProxyInstance(Flyable.class, new FlyTimeJdkInvocationHandler ( new Bird () ));
       proxyFlyable.fly ();
    }
}
