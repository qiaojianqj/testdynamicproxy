package staticproxy.proxy.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bo.Bird;

/**
 * @description: This is wrapper class extends from Bird
 * @author: Qiao.Jian
 * @create: 2018-09-19 16:28
 */
public class BirdFlyTime extends Bird {
    private static Logger logger = LoggerFactory.getLogger ( BirdFlyTime.class );
    @Override
    public void fly() {
        long start = System.currentTimeMillis ();
        super.fly ();
        long end = System.currentTimeMillis ();
        logger.info ( "Bird flying time: {}", end - start );
    }
}
