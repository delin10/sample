package nil.ed;

import com.sun.org.apache.xerces.internal.util.SecurityManager;

import java.io.File;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(new File("").getAbsoluteFile());
        //Arrays.stream(Security.getProviders()).map(Provider::getServices).forEach(System.out::println);
    }
}
