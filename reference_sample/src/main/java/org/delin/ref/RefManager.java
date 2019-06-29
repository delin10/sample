package org.delin.ref;

import java.lang.ref.*;
import java.util.HashMap;
import java.util.Map;

public class RefManager {
    private enum RefConst {SOFTREF, WEAKREF, PHANTOMREF}

    ;
    private ReferenceQueue<Object> refQ;
    private Map<Reference, GCCallBack> callbacks;

    public Reference registerPhantomReference(Object o, GCCallBack callBack) {
        return register(o, callBack, RefConst.PHANTOMREF);
    }

    public Reference registerWeakReference(Object o, GCCallBack callBack) {
        return register(o, callBack, RefConst.WEAKREF);
    }

    public Reference registerSoftReference(Object o, GCCallBack callBack) {
        return register(o, callBack, RefConst.SOFTREF);
    }


    public GCCallBack getCallBack(Reference ref) {
        return callbacks.get(ref);
    }

    public Reference pollNonBlock() {
        if (refQ != null) {
            return refQ.poll();
        }
        return null;
    }

    public void consume() {
        if (refQ != null) {
            try {
                Reference ref = refQ.remove();
                GCCallBack callBack=callbacks.remove(ref);
                if (callBack!=null) {
                    callBack.callback(ref);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Reference pollBlock(long timeout) {
        if (refQ != null) {
            try {
                return refQ.remove(timeout);
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }


    private void newRefQIfNull() {
        if (refQ == null) {
            refQ = new ReferenceQueue<Object>();
        }
    }

    private Reference register(Object o, GCCallBack callBack, RefConst type) {
        newRefQIfNull();
        Reference ref = null;
        switch (type) {
            case SOFTREF:
                ref = new SoftReference(o, refQ);
                break;
            case WEAKREF:
                ref = new WeakReference(o, refQ);
                break;
            case PHANTOMREF:
                ref = new PhantomReference(o, refQ);
        }

        putCallBackIfNotExist(ref, callBack);
        return ref;
    }


    private void putCallBackIfNotExist(Reference ref, GCCallBack callBack) {
        if (callbacks == null) {
            callbacks = new HashMap<Reference, GCCallBack>();
        }
        GCCallBack c = callbacks.get(ref);
        if (c == null) {
            callbacks.put(ref, callBack);
        }
    }
}
