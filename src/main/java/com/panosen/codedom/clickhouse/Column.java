package com.panosen.codedom.clickhouse;

public class Column {

    private String columnName;

    private String columnAs;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAs() {
        return columnAs;
    }

    public void setColumnAs(String columnAs) {
        this.columnAs = columnAs;
    }
}
