package org.delin.stateless;

import javax.ejb.Remote;

@Remote
public interface GetTimeSessionBeanRemote {
    String getTime();
}
