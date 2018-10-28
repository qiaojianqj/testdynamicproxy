package staticproxy.proxy.combination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bo.Flyable;

/**
 * @description: This class implements Flyable interface
 * @author: Qiao.Jian
 * @create: 2018-09-19 16:38
 */
public class BirdFlyTimeProxy implements Flyable {
    private static Logger logger = LoggerFactory.getLogger ( BirdFlyTimeProxy.class );
    private Flyable flyable;

    public BirdFlyTimeProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
       long start = System.currentTimeMillis ();
       flyable.fly ();
       long end = System.currentTimeMillis ();
       logger.info ( "Bird flying time: {}", end - start );
    }
}
