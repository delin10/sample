package org.delin.ejb;

import javax.ejb.Remote;

@Remote
public interface TestBeanRemote {
    String get();
}
