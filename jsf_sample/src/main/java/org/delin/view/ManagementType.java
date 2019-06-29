package org.delin.view;

//管理->行政管理、工商管理、金融管理、其它；其它->无。
public enum ManagementType {
    ADMINISTRATIVE("行政管理", 0), BUSSINESS("工商管理", 1), FINANCE("金融管理", 2), OTHER("其它", 3);
    private String label;
    private int value;

    private ManagementType(String label, int value) {
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

    public String getName() {
        return name();
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
