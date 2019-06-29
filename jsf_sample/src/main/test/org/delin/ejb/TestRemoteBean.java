package org.delin.ejb;

import org.delin.ejb.impl.CountLoginTimeBean;
import org.delin.ejb.impl.ListBooksBean;
import org.delin.ejb.impl.TestBean;
import org.delin.util.ejb.EJBLookUpUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class TestRemoteBean {
    public static void main(String[] args) throws Exception {
        TestBeanRemote bean0 = (TestBeanRemote) EJBLookUpUtil.lookup(TestBeanRemote.class, TestBean.class, "Loser", "", false);
        ListBooksBeanRemote bean = (ListBooksBeanRemote) EJBLookUpUtil.lookup(ListBooksBeanRemote.class, ListBooksBean.class, "Loser", "", false);
        CountLoginTimeBeanRemote countLoginTimeBeanRemote = (CountLoginTimeBeanRemote) EJBLookUpUtil.lookup(CountLoginTimeBeanRemote.class, CountLoginTimeBean.class, "Loser", "", false);
        CountLoginTimeBeanRemote countLoginTimeBeanRemote2 = (CountLoginTimeBeanRemote) EJBLookUpUtil.lookup(CountLoginTimeBeanRemote.class, CountLoginTimeBean.class, "Loser", "", false);
        System.out.println(bean.listAllBooks());
        System.out.println(countLoginTimeBeanRemote.getCount());
        countLoginTimeBeanRemote.inc();
        System.out.println(countLoginTimeBeanRemote2.getCount());
    }

    private static TestBeanRemote lookupRemoteStatelessEjbBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        final String appName = "";
        final String moduleName = "Loser";
        final String distinctName = "";
        final String beanName = "TestBean";
        final String viewClassName = TestBeanRemote.class.getName();
        final String namespace = "ejb:" + appName + "/" + moduleName
                + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        System.out.println(namespace);
        return (TestBeanRemote) context.lookup(namespace);
    }
}
