package org.delin.ejb.impl;

import org.delin.ejb.TestBeanRemote;

import javax.ejb.Stateless;

@Stateless
public class TestBean implements TestBeanRemote {
    @Override
    public String get() {
        return "testbean";
    }
}
