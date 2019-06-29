package test;

import org.delin.stateless.GetTimeSessionBean;
import org.delin.stateless.GetTimeSessionBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;

public class TestRemoteBean {
    public static void main(String[]args)throws Exception{
        GetTimeSessionBeanRemote bean=lookupRemoteStatelessEjbBean();
        String time=bean.getTime();
        System.out.println(time);
    }

    private static GetTimeSessionBeanRemote lookupRemoteStatelessEjbBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        final String appName = "";
        final String moduleName = "EJBTestt_war_exploded";
        final String distinctName = "";
        final String beanName = "GetTimeSessionBean";
        final String viewClassName = GetTimeSessionBeanRemote.class.getName();
        final String namespace = "ejb:" + appName + "/" + moduleName
                + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        System.out.println(namespace);
        return (GetTimeSessionBeanRemote) context.lookup(namespace);
        /*
           return (RemoteCounter) context.lookup("ejb:" + appName + "/" + moduleName
            + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
        */
    }
}
