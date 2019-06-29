package org.delin.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public interface GCCallBack {
    public void callback(Reference ref);
}
