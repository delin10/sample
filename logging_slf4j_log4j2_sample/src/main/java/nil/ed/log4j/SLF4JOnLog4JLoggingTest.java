package nil.ed.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JOnLog4JLoggingTest {
    public static void main(String[] args) {
        Logger logger=LoggerFactory.getLogger(SLF4JOnLog4JLoggingTest.class);

        logger.info("ok");
        logger.debug("ok");
        logger.warn("ok");
        logger.trace("ok");
        logger.error("ok");
    }
}
