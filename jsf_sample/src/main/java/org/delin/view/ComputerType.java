package org.delin.view;

import org.delin.collection.tree.BalancedTreeNode;
import org.delin.entities.BookEntity;

public enum ComputerType  {
    SOFTWARD_ARCHITECTURE("软件工程", 0), COMPUTER_NETWORK("计算机网络", 1), PROGRAM_LANG("编程语言", 2), OTHER("其它", 3);
    private String label;
    private int value;

    private ComputerType(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName(){
        return name();
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
