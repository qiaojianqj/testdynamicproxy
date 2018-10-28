package staticproxy.proxy.combination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bo.Flyable;

/**
 * @description: This class implements Flyable interface
 * @author: Qiao.Jian
 * @create: 2018-09-19 16:44
 */
public class BirdLogProxy implements Flyable {
    private static Logger logger = LoggerFactory.getLogger ( BirdLogProxy.class );
    private Flyable flyable;

    public BirdLogProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        logger.info ( "Bird fly start..." );
        flyable.fly ();
        logger.info ( "Bird fly end..." );
    }
}
