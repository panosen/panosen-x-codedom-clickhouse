package com.panosen.codedom.clickhouse.builder;

import com.panosen.codedom.clickhouse.Column;

public class ColumnBuilder {

    private final Column column = new Column();

    public Column getColumn() {
        return column;
    }

    public ColumnBuilder name(String name) {
        column.setColumnName(name);
        return this;
    }

    public ColumnBuilder as(String as) {
        column.setColumnAs(as);
        return this;
    }
}
