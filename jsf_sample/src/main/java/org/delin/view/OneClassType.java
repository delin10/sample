package org.delin.view;

public enum OneClassType {
    COMPUTER("计算机", 0), LITERATUER("文学", 1), MANAGEMENT("管理", 2), OTHER("其它", 3);
    private String label;
    private int value;

    private OneClassType(String label, int value) {
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
        return this.name();
    }
}
