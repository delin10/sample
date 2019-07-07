package nil.ed.jdk144;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LogManagerConfig {
    //一般放在static初始化域
    public static void config(String resource) {
        // 读取配置文件
        ClassLoader cl = LogManager.class.getClassLoader();
        InputStream inputStream = null;
        if (cl != null) {
            inputStream = cl.getResourceAsStream(resource);
        } else {
            inputStream = ClassLoader.getSystemResourceAsStream(resource);
        }

        //配置
        java.util.logging.LogManager logManager = java.util.logging.LogManager.getLogManager();
        try {
            // 重新初始化日志属性并重新读取日志配置。
            logManager.readConfiguration(inputStream);
        } catch (SecurityException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
