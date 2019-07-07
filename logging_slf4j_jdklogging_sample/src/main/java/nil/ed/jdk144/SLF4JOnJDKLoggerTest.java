package nil.ed.jdk144;

import nil.ed.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SLF4JOnJDKLoggerTest {
    public static void main( String[] args ){
        //LogManagerConfig.config("logging.properties");
        //System.setProperty("java.util.logging.config.file", App.class.getClassLoader().getResource("logging.properties").getFile());
        //LogManager里读取配置文件用的是FileInputStream
        //不会读取在class根目录下的文件
        System.out.println(new File("").getAbsolutePath());
        System.out.println(System.getProperty("java.util.logging.config.file"));
        Logger logger = LoggerFactory.getLogger(App.class);

        logger.info("ok");
    }
}
