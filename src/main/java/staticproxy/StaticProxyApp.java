package staticproxy;

import bo.Bird;
import staticproxy.proxy.combination.BirdFlyTimeProxy;
import staticproxy.proxy.combination.BirdLogProxy;

/**
 * @description: This is main used to test static timeproxy
 * 静态代理至少有以下两个局限性问题：

 * 如果同时代理多个类，依然会导致类无限制扩展
 * 如果类中有多个方法，同样的逻辑需要反复实现
 *
 * @author: Qiao.Jian
 * @create: 2018-09-19 16:47
 */
public class StaticProxyApp {
    public static void main(String[] args) {
        Bird bird = new Bird ();

        //使用组合实现的静态代理可以同时使用并灵活调换代理的执行顺序
        BirdLogProxy birdLogProxy = new BirdLogProxy ( bird );
        BirdFlyTimeProxy birdFlyTimeProxy = new BirdFlyTimeProxy ( birdLogProxy );
        birdFlyTimeProxy.fly ();

        //BirdFlyTimeProxy birdFlyTimeProxy = new BirdFlyTimeProxy ( bird );
        //BirdLogProxy birdLogProxy = new BirdLogProxy ( birdFlyTimeProxy );
        //birdLogProxy.fly ();

        //使用继承实现的静态代理，不能同时使用
        //BirdLog birdLog = new BirdLog ();
        //birdLog.fly ();
        //BirdFlyTime birdFlyTime = new BirdFlyTime ();
        //birdFlyTime.fly ();
    }
}
