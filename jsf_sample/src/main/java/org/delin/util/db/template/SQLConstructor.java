package org.delin.util.db.template;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SQLConstructor {
    private StringBuilder builder;

    public SQLConstructor() {
        this.builder = new StringBuilder();
    }

    public SQLConstructor select(String... cols) {
        builder.append("select ");
        builder.append(Arrays.stream(cols).collect(Collectors.joining(",")));
        builder.append(" from ");
        return this;
    }

    public SQLConstructor from(String... tables) {
        builder.append(Arrays.stream(tables).collect(Collectors.joining(",")));
        return this;
    }
}
