package org.delin.ejb.impl;

import org.delin.ejb.CountLoginTimeBeanRemote;

import javax.ejb.Singleton;

@Singleton
public class CountLoginTimeBean implements CountLoginTimeBeanRemote {
    private int count = 0;

    @Override
    public int inc() {
        return ++count;
    }

    @Override
    public int getCount() {
        return count;
    }
}
