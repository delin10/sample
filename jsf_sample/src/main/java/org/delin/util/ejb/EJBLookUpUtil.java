package org.delin.util.ejb;

import org.delin.util.lambda.RuntimeExceptionExec;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class EJBLookUpUtil {
    public static Object lookup(Class<?> ejbInterface, Class<?> impl, String moduleName, String appName,boolean isStateful) {
        return RuntimeExceptionExec.exec(() -> {
                    final Hashtable jndiProperties = new Hashtable();
                    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
                    final Context context = new InitialContext(jndiProperties);
                    final String distinctName = "";
                    final String beanName = impl.getSimpleName();
                    final String viewClassName = ejbInterface.getName();
                    final String namespace = "ejb:" + appName + "/" + moduleName
                            + "/" + distinctName + "/" + beanName + "!" + viewClassName+(isStateful?"?stateful":"");
                    return context.lookup(namespace);
                }
        );

    }
}
