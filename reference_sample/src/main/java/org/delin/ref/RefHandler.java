package org.delin.ref;

import java.lang.ref.Reference;

public class RefHandler implements Runnable{
    private RefManager manager;

    public RefHandler(RefManager manager) {
        this.manager = manager;
    }

    public void run() {
        while(true){
            manager.consume();
        }
    }
}
