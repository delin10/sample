package org.delin.ejb;

import javax.ejb.Remote;

@Remote
public interface CountLoginTimeBeanRemote {
    int inc();

    int getCount();
}
