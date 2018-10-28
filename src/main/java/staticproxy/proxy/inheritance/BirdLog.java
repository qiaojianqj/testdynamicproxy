package staticproxy.proxy.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bo.Bird;

/**
 * @description: This is wrapper class extends from Bird
 * @author: Qiao.Jian
 * @create: 2018-09-19 17:05
 */
public class BirdLog extends Bird {
    private static Logger logger = LoggerFactory.getLogger ( BirdLog.class );

    @Override
    public void fly() {
        logger.info ( "Bird fly start..." );
        super.fly ();
        logger.info ( "Bird fly end..." );
    }
}
