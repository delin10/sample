package nil.ed;

import org.junit.runner.Description;

public class TestName extends org.junit.rules.TestName {
    private String fName;

    @Override
    protected void starting(Description d) {
        fName = d.getClassName() + ":" + d.getMethodName();
    }

    @Override
    public String getMethodName() {
        return fName;
    }
}
