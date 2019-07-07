package nil.ed;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class TestSLF4J {

    @Test
    public void testJDK14() {
        System.out.println(System.getProperty("java.util.logging.config.file"));
        Logger logger = LoggerFactory.getLogger(TestSLF4J.class);
        logger.info("ok");
    }
}
