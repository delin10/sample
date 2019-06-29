package org.delin.view;

//文学->小说、散文、诗词、其它；
public enum LiteratureType {
    NOVEL("小说", 0), PROSE("散文", 1), POETRY("诗词", 2), OTHER("其它", 3);
    private String label;
    private int value;

    private LiteratureType(String label, int value) {
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
