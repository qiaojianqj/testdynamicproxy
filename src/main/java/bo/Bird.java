package bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Bird implements Flyable {
    private static Logger logger = LoggerFactory.getLogger(Bird.class);
    public void fly() {
        logger.info ( "Bird flying" );
        try {
           Thread.sleep ( new Random ( ).nextInt (100) );
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

}